package com.hit.cggb.archimedes.util;

import com.hit.cggb.archimedes.config.ElasticsearchProperties;
import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:24
 * @description Elasticsearch 工具类
 */
@Lazy
@Component("elasticsearchUtil")
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchUtil {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchUtil.class);

    // key为集群名，value为对应的客户端连接
    private static Map<String, TransportClient> clientMap = new HashMap<>();

    private ElasticsearchProperties elasticsearchProperties;

    @Autowired
    public ElasticsearchUtil(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        Map<String, String[]> clusterAndIpList = elasticsearchProperties.getClusterAndIpList();
        for (String clusterName : clusterAndIpList.keySet()) {
            Settings settings = Settings.builder().put("client.transport.sniff", false)
                    .put("cluster.name", clusterName)
                    .put("client.transport.ping_timeout", 60, TimeUnit.SECONDS)
                    .build();
            TransportClient client = new PreBuiltTransportClient(settings);
            logger.info("try to connect elastic search, cluster name is {}", clusterName);
            for (String ipPortStr : clusterAndIpList.get(clusterName)) {
                String[] ipPort = ipPortStr.split(":");
                String ip = ipPort[0];
                int port = Integer.parseInt(ipPort[1]);
                TransportAddress transportAddress = new TransportAddress((new InetSocketAddress(ip, port)));
                client.addTransportAddress(transportAddress);
            }
            clientMap.put(clusterName, client);
        }
    }

    @PreDestroy
    public void close() {
        clientMap.values().forEach(TransportClient::close);
    }

    /**
     * 获取索引信息
     *
     * @param clusterName 集群名称
     * @param indexName   索引名称
     */
    public Map<String, String> getIndexInfoByClusterNameAndIndexName(String clusterName, String indexName) {
        Map<String, String> indexInfoMap = new HashMap<>();

        // 获取主节点IP和端口号列表
        Map<String, String[]> masterNodeIpAndPortMap = elasticsearchProperties.getClusterAndIpList();
        // 拼接URL，替换 9300 端口为 9200
        String indexInfoUrl = "http://" + masterNodeIpAndPortMap.get(clusterName)[0].replace("9300", "9200") + "/_cat/indices/";
        RestTemplate restTemplate = new RestTemplate();

        try {
            String restfulResult = restTemplate.getForObject(indexInfoUrl + indexName, String.class);
            if (restfulResult != null) {
                String[] restfulResultArray = restfulResult.split(" ");
                indexInfoMap.put("health", restfulResultArray[0]);
                indexInfoMap.put("status", restfulResultArray[1]);
                indexInfoMap.put("index", restfulResultArray[2]);
                indexInfoMap.put("uuid", restfulResultArray[3]);
                indexInfoMap.put("pri", restfulResultArray[4]);
                indexInfoMap.put("rep", restfulResultArray[5]);
                indexInfoMap.put("docs.count", restfulResultArray[6]);
                indexInfoMap.put("docs.deleted", restfulResultArray[7]);
                indexInfoMap.put("store.size", restfulResultArray[8]);
                indexInfoMap.put("pri.store.size", restfulResultArray[9].replace("\n", ""));
                return indexInfoMap;
            }
        } catch (Throwable e) {
            logger.error("call { " + indexInfoUrl + indexName + " } come cross a error, message is " + e);
        }
        return indexInfoMap;
    }

    /**
     * 获取目标索引的时间序列
     *
     * @param detectionParam 查询参数
     * @return 目标时间序列
     */
    public List<Map<String, Object>> getTimeSeriesFromEsIndex(OutlierDetectionParam detectionParam) {
        // 初始化返回结果
        List<Map<String, Object>> timeSeriesMapList = new ArrayList<>();

        // 获取集群名称
        List<String> clusterNameList = new ArrayList<>(clientMap.keySet());
        String clusterName = clusterNameList.get(0);

        try {
            //多条件查询
            SearchResponse searchResponse = clientMap.get(clusterName).prepareSearch(detectionParam.getIndexType()).setTypes(detectionParam.getIndexType())
                    .setQuery(QueryBuilders.boolQuery()
                            .must(QueryBuilders.rangeQuery("time").from(ThreadSafeDateUtil.convertJavaTime2EsTime(detectionParam.getStartTime()))
                                    .to(ThreadSafeDateUtil.convertJavaTime2EsTime(detectionParam.getEndTime()))))
                    .setExplain(true)
                    .setSize(50)
                    .execute()
                    .actionGet();

            SearchHits searchHits = searchResponse.getHits();
            if (searchHits.getTotalHits() > 0) {
                SearchHit[] hits = searchHits.getHits();
                // 格式化结果数据
                for (SearchHit hit : hits) {
                    Double aDoubleValue = (Double) hit.getSourceAsMap().get("currentValue");
                    long dateLong = ThreadSafeDateUtil.convertEsTime2JavaTime((String) hit.getSourceAsMap().get("time"));
                    String dateStr = ThreadSafeDateUtil.formatDateTime(new Date(dateLong));
                    Map<String, Object> timeSeriesMap = new HashMap<>();
                    timeSeriesMap.put("value", aDoubleValue);
                    timeSeriesMap.put("date", dateStr);
                    timeSeriesMapList.add(timeSeriesMap);
                }
            }
        } catch (Throwable e) {
            logger.error("get time series from es index come across a error, message is " + e);
        }
        return timeSeriesMapList;
    }
}
