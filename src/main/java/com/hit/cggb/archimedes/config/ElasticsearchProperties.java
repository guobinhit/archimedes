package com.hit.cggb.archimedes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:25
 * @description
 */
@Component("elasticsearchProperties")
@ConfigurationProperties(prefix = "data.elasticsearch")
public class ElasticsearchProperties {
    private Map<String, String[]> clusterAndIpList;

    public Map<String, String[]> getClusterAndIpList() {
        return clusterAndIpList;
    }

    public void setClusterAndIpList(Map<String, String[]> clusterAndIpList) {
        this.clusterAndIpList = clusterAndIpList;
    }
}
