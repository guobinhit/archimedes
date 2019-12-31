package com.hit.cggb.archimedes.service;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.entity.TrendPredictionParam;
import com.hit.cggb.archimedes.entity.TrendPredictionResult;
import com.hit.cggb.archimedes.enumtype.PredictionAlgorithmTypeEnum;
import com.hit.cggb.archimedes.predict.TrendPrediction;
import com.hit.cggb.archimedes.predict.impl.FirstExponentialSmoothingPrediction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/31,下午2:53
 * @description 趋势预测服务类
 */
public class TrendPredictionService {

    private static Logger logger = LoggerFactory.getLogger(TrendPredictionService.class);

    /**
     * 趋势预测方法
     *
     * @param trendPredictionParam 预测参数
     * @return 预测结果
     */
    public TrendPredictionResult trendPredict(TrendPredictionParam trendPredictionParam) {
        // 异常点检测
        OutlierDetectionResult detectionResult = (new OutlierDetectionService()).outlierDetect(trendPredictionParam.getOutlierDetectionParam());
        List<Map<String, Object>> dataMapList = detectionResult.getDataMapList();
        List<Integer> outlierIndex = detectionResult.getOutlierIndexList();
        if (dataMapList != null && dataMapList.size() > 0) {
            // 待预测数据列表
            List<Double> processedDataList = new ArrayList<>();
            for (int index = 0; index < dataMapList.size(); index++) {
                if (outlierIndex != null && outlierIndex.size() > 0) {
                    // 过滤异常点
                    if (!outlierIndex.contains(index)) {
                        processedDataList.add((Double) dataMapList.get(index).get("value"));
                    }
                } else {
                    processedDataList.add((Double) dataMapList.get(index).get("value"));
                }
            }

            // TODO 预测算法
            if (processedDataList.size() > 0) {
                TrendPrediction trendPrediction;
                if (PredictionAlgorithmTypeEnum.FIRST_EXPONENTIAL_SMOOTHING.equals(trendPredictionParam.getPredictionAlgorithmType())) {
                    trendPrediction = new FirstExponentialSmoothingPrediction();
                } else {
                    throw new IllegalArgumentException(trendPredictionParam.getPredictionAlgorithmType().name() + " is not support now");
                }

            }
        }
        return null;
    }
}
