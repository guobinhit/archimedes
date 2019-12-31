package com.hit.cggb.archimedes.service;

import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;
import com.hit.cggb.archimedes.enumtype.DetectionAlgorithmTypeEnum;
import com.hit.cggb.archimedes.outlier.OutlierDetection;
import com.hit.cggb.archimedes.outlier.impl.*;
import com.hit.cggb.archimedes.util.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/27,上午11:20
 * @description 异常点探测服务类
 */
public class OutlierDetectionService {

    private static Logger logger = LoggerFactory.getLogger(OutlierDetectionService.class);

    /**
     * 异常点探测方法
     *
     * @param outlierDetectionParam 探测参数
     * @return 探测结果
     */
    public OutlierDetectionResult outlierDetect(OutlierDetectionParam outlierDetectionParam) {
        // 校验参数
        if (outlierDetectionParam.getDataMapList() == null
                || outlierDetectionParam.getDataMapList().size() == 0) {
            throw new IllegalArgumentException("data set can not be null and size must be larger 0");
        }

        logger.info("outlier detection query param is " + outlierDetectionParam.toString());

        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        // 获取目标时间序列
        List<Map<String, Object>> dataMapList = outlierDetectionParam.getDataMapList();

        // 处理日期排序参数
        List<Map<String, Object>> orderDataMapList;
        if (OrderTypeEnum.ASC.equals(outlierDetectionParam.getOrderType())) {
            orderDataMapList = new SortHelper<>().sortListByMapDate(dataMapList, OrderTypeEnum.ASC);
        } else if (OrderTypeEnum.DESC.equals(outlierDetectionParam.getOrderType())) {
            orderDataMapList = new SortHelper<>().sortListByMapDate(dataMapList, OrderTypeEnum.DESC);
        } else {
            orderDataMapList = dataMapList;
        }

        // 对数据集进行校验，并根据算法类型实例化异常点探测类
        if (orderDataMapList != null && orderDataMapList.size() > 0) {
            // 获取异常点探测实例
            OutlierDetection outlierDetection;
            if (DetectionAlgorithmTypeEnum.QUARTILE.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new QuartileOutlierDetection();
            } else if (DetectionAlgorithmTypeEnum.SIGMA3.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new Sigma3OutlierDetection();
            } else if (DetectionAlgorithmTypeEnum.ZSCORE.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new ZscoreOutlierDetection();
            } else if (DetectionAlgorithmTypeEnum.DBSCAN.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new DbscanDetection();
            } else if (DetectionAlgorithmTypeEnum.LOF.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new LofDetection();
            } else if (DetectionAlgorithmTypeEnum.STCR.equals(outlierDetectionParam.getDetectionAlgorithmType())) {
                outlierDetection = new StcrDetection();
            } else {
                throw new IllegalArgumentException(outlierDetectionParam.getDetectionAlgorithmType().name() + " is not support now");
            }
            // 探测异常点
            detectionResult = outlierDetection.detect(orderDataMapList);
        }
        return detectionResult;
    }
}
