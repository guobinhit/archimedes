package com.hit.cggb.archimedes.service;


import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;
import com.hit.cggb.archimedes.enumtype.OutlierDetectionTypeEnum;
import com.hit.cggb.archimedes.outlier.OutlierDetection;
import com.hit.cggb.archimedes.outlier.impl.*;
import com.hit.cggb.archimedes.util.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
     * @param detectionParam 探测参数
     * @return 探测结果
     */
    public OutlierDetectionResult outlierDetect(OutlierDetectionParam detectionParam) {
        // 校验参数
        if (detectionParam.getDataMapList() == null || detectionParam.getDataMapList().size() == 0) {
            throw new IllegalArgumentException("data set can not be null and size must be larger 0");
        }

        logger.info("outlier detection query param is " + detectionParam.toString());

        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        // 获取目标时间序列
        List<Map<String, Object>> dataMapList = detectionParam.getDataMapList();

        // 处理日期排序参数
        List<Map<String, Object>> orderDateMapList;
        if (detectionParam.getOrderType() == null || "".equals(detectionParam.getOrderType())) {
            orderDateMapList = dataMapList;
        } else if (OrderTypeEnum.ASC.name().equals(detectionParam.getOrderType())) {
            orderDateMapList = new SortHelper<>().sortListByMapDate(dataMapList, OrderTypeEnum.ASC);
        } else if (OrderTypeEnum.DESC.name().equals(detectionParam.getOrderType())) {
            orderDateMapList = new SortHelper<>().sortListByMapDate(dataMapList, OrderTypeEnum.DESC);
        } else {
            throw new IllegalArgumentException("meet unknown order type of " + detectionParam.getAlgorithmType()
                    + ", order type must be one of " + Arrays.toString(OutlierDetectionTypeEnum.values()));
        }

        // 对数据集进行校验，并根据算法类型实例化异常点探测类
        if (orderDateMapList != null && orderDateMapList.size() > 0) {
            // 获取异常点探测实例
            OutlierDetection outlierDetection;
            if (OutlierDetectionTypeEnum.QUARTILE.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new QuartileOutlierDetection();
            } else if (OutlierDetectionTypeEnum.SIGMA3.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new Sigma3OutlierDetection();
            } else if (OutlierDetectionTypeEnum.ZSCORE.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new ZscoreOutlierDetection();
            } else if (OutlierDetectionTypeEnum.DBSCAN.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new DbscanDetection();
            } else if (OutlierDetectionTypeEnum.LOF.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new LofDetection();
            } else if (OutlierDetectionTypeEnum.STCR.name().equals(detectionParam.getAlgorithmType())) {
                outlierDetection = new StcrDetection();
            } else {
                throw new IllegalArgumentException("meet unknown algorithm type of " + detectionParam.getAlgorithmType()
                        + ", algorithm type must be one of " + Arrays.toString(OutlierDetectionTypeEnum.values()));
            }
            // 探测异常点
            detectionResult = outlierDetection.detect(orderDateMapList);
        }
        return detectionResult;
    }
}
