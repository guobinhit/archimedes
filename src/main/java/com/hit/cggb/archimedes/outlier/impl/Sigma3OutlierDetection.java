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
 * @date 2019/12/26,下午6:34
 * @description 3σ异常点探测算法
 */
public class Sigma3OutlierDetection implements OutlierDetection {
    @Override
    public OutlierDetectionResult detect(List<Map<String, Object>> dataMapList) {
        // 初始化返回参数
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        List<Integer> outlierIndexList = new ArrayList<>();

        // 限制数据集中的数据量至少为3，以保证去掉最大值和最小值后，可以进行有效计算
        if (dataMapList.size() >= 3) {
            // 将数据存入数组以便计算
            double[] dataArray = new double[dataMapList.size()];
            for (int i = 0; i < dataMapList.size(); i++) {
                dataArray[i] = (Double) dataMapList.get(i).get("value");
            }
            // 从小到大排序
            Arrays.sort(dataArray);

            double sumValue = 0.0;
            // 去掉最大值和最下值
            for (int i = 1; i < dataArray.length - 2; i++) {
                sumValue += dataArray[i];
            }
            // 均值
            double meanValue = sumValue / (dataArray.length - 2);
            // 差值平方和
            double deviationSquareSum = 0.0;
            for (int i = 1; i < dataArray.length - 2; i++) {
                deviationSquareSum = (dataArray[i] - meanValue) * (dataArray[i] - meanValue);
            }
            // 标准差
            double standardDeviation = Math.sqrt(deviationSquareSum / (dataArray.length - 2));
            // 上下截断点
            double lowerCutoffPoint = meanValue - standardDeviation * 3;
            double upperCutoffPoint = meanValue + standardDeviation * 3;

            // 校验异常点
            for (int i = 0; i < dataMapList.size(); i++) {
                double currentValue = (Double) dataMapList.get(i).get("value");
                if (lowerCutoffPoint > currentValue || currentValue > upperCutoffPoint) {
                    outlierIndexList.add(i);
                }
            }
        }

        detectionResult.setDataMapList(dataMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }
}
