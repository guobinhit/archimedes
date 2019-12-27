package com.hit.cggb.archimedes.outlier.impl;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.outlier.OutlierDetection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:29
 * @description 短期环比异常点检查算法
 */
public class StcrDetection implements OutlierDetection {
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

        // 初始化阈值百分比、阈值窗口以及阈值数量
        double thresholdPercent = 0.15;
        int thresholdWindow = 7;
        int thresholdCount = 4;

        // 当数据量大于阈值窗口时进行处理
        if (timeSeries.length > thresholdWindow) {
            // 校验异常点
            for (int i = thresholdWindow; i < timeSeries.length; i++) {
                int issueCount = 0;
                for (int k = 1, j = i - k; k < thresholdWindow; k++) {
                    if ((Math.abs(timeSeries[i] - timeSeries[j]) / timeSeries[j]) > thresholdPercent) {
                        issueCount++;
                    }
                }
                if (issueCount > thresholdCount) {
                    outlierIndexList.add(i);
                }
            }
        }

        detectionResult.setDataMapList(timeSeriesMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }
}
