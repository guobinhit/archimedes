package com.hit.cggb.archimedes.service;


import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.enumtype.OutlierDetectionTypeEnum;
import com.hit.cggb.archimedes.outlier.OutlierDetection;
import com.hit.cggb.archimedes.outlier.impl.*;
import com.hit.cggb.archimedes.util.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OutlierDetectionService {
    private static Logger logger = LoggerFactory.getLogger(OutlierDetectionService.class);

    public OutlierDetectionResult outlierDetect(OutlierDetectionParam detectionParam) {
        logger.info("outlier detection query param is " + detectionParam.toString());
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        // 获取目标时间序列
//        List<Map<String, Object>> timeSeriesMapList = elasticsearchUtil.getTimeSeriesFromEsIndex(detectionParam);
        // 按时间从早到晚排序
//        List<Map<String, Object>> ascTimeSeriesMapList = new SortHelper<>().sortListByMapDate(timeSeriesMapList);
        List<Map<String, Object>> ascTimeSeriesMapList = null;
        // 对数据集进行校验，并根据算法类型实例化异常点探测类
        if (ascTimeSeriesMapList != null && ascTimeSeriesMapList.size() > 0) {
            // 获取异常点探测实例
            OutlierDetection outlierDetection = null;
            if (OutlierDetectionTypeEnum.QUARTILE.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new QuartileOutlierDetection();
            } else if (OutlierDetectionTypeEnum.SIGMA3.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new Sigma3OutlierDetection();
            } else if (OutlierDetectionTypeEnum.ZSCORE.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new ZscoreOutlierDetection();
            } else if (OutlierDetectionTypeEnum.DBSCAN.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new DbscanDetection();
            } else if (OutlierDetectionTypeEnum.LOF.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new LofDetection();
            } else if (OutlierDetectionTypeEnum.STCR.getValue().equalsIgnoreCase(detectionParam.getAlgorithmType())) {
                outlierDetection = new StcrDetection();
            } else {
                logger.error("meet unknown algorithm type { " + detectionParam.getAlgorithmType() + " }");
            }
            // 探测异常点
            if (outlierDetection != null) {
                detectionResult = outlierDetection.detect(ascTimeSeriesMapList);
            }
        }
        return detectionResult;
    }
}
