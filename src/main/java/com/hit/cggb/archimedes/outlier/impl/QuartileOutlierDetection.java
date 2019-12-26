package com.hit.cggb.archimedes.outlier.impl;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.outlier.OutlierDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 11/18/19,6:03 PM
 * @description 四分位异常点探测算法
 */
public class QuartileOutlierDetection implements OutlierDetection {
    @Override
    public OutlierDetectionResult detect(List<Map<String, Object>> timeSeriesMapList) {
        // 初始化返回参数
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        List<Integer> outlierIndexList = new ArrayList<>();

        // 将数据存入数组以便计算
        double[] timeSeries = new double[timeSeriesMapList.size()];
        for (int i = 0; i < timeSeriesMapList.size(); i++) {
            timeSeries[i] = (Double) timeSeriesMapList.get(i).get("value");
        }

        // 从小到大排序
        Arrays.sort(timeSeries);

        // 上下四分位点
        double lowerQuartile = timeSeries[(int) (timeSeries.length * 0.25)];
        double upperQuartile = timeSeries[(int) (timeSeries.length * 0.75)];
        // 上下四分位点的差值
        double distribution = upperQuartile - lowerQuartile;
        // 上下截断点
        double upperCutoffPoint = upperQuartile + distribution * 1.5;
        double lowerCutoffPoint = lowerQuartile - distribution * 1.5;

        // 校验异常点
        for (int i = 0; i < timeSeriesMapList.size(); i++) {
            double currentValue = (Double) timeSeriesMapList.get(i).get("value");
            if (lowerCutoffPoint > currentValue || currentValue > upperCutoffPoint) {
                outlierIndexList.add(i);
            }
        }

        detectionResult.setDataMapList(timeSeriesMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }
}
