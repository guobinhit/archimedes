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
 * @date 11/20/19,2:55 PM
 * @description Z分数异常点探测算法
 */
public class ZscoreOutlierDetection implements OutlierDetection {
    @Override
    public OutlierDetectionResult detect(List<Map<String, Object>> timeSeriesMapList) {
        // 初始化返回参数
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        List<Integer> outlierIndexList = new ArrayList<>();

        // 限制数据集中的数据量至少为3，以保证去掉最大值和最小值后，可以进行有效计算
        if (timeSeriesMapList.size() >= 3) {
            // 将数据存入数组以便计算
            double[] timeSeries = new double[timeSeriesMapList.size()];
            for (int i = 0; i < timeSeriesMapList.size(); i++) {
                timeSeries[i] = (Double) timeSeriesMapList.get(i).get("value");
            }
            // 从小到大排序
            Arrays.sort(timeSeries);

            double sumValue = 0.0;
            // 去掉最大值和最下值
            for (int i = 1; i < timeSeries.length - 2; i++) {
                sumValue += timeSeries[i];
            }
            // 均值
            double meanValue = sumValue / (timeSeries.length - 2);
            // 差值平方和
            double deviationSquareSum = 0.0;
            for (int i = 1; i < timeSeries.length - 2; i++) {
                deviationSquareSum = (timeSeries[i] - meanValue) * (timeSeries[i] - meanValue);
            }
            // 标准差
            double standardDeviation = Math.sqrt(deviationSquareSum / (timeSeries.length - 2));

            // 校验异常点
            for (int i = 0; i < timeSeriesMapList.size(); i++) {
                double currentValue = (Double) timeSeriesMapList.get(i).get("value");
                // 求Z分数并进行判断
                double zScore = Math.abs((currentValue - meanValue) / standardDeviation);
                // 根据经验，分数阈值一般为 2.5、3 或者 3.5
                if (zScore > 3.5) {
                    outlierIndexList.add(i);
                }
            }
        }

        detectionResult.setTimeSeriesMapList(timeSeriesMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }
}
