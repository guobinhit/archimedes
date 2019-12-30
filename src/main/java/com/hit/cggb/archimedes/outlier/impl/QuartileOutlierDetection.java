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
 * @date 2019/12/26,下午6:03
 * @description 四分位异常点探测算法
 */
public class QuartileOutlierDetection implements OutlierDetection {
    @Override
    public OutlierDetectionResult detect(List<Map<String, Object>> dataMapList) {
        // 初始化返回参数
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        List<Integer> outlierIndexList = new ArrayList<>();

        // 将数据存入数组以便计算
        double[] dataArray = new double[dataMapList.size()];
        for (int i = 0; i < dataMapList.size(); i++) {
            dataArray[i] = (Double) dataMapList.get(i).get("value");
        }

        // 从小到大排序
        Arrays.sort(dataArray);

        // 上下四分位点
        double lowerQuartile = dataArray[(int) (dataArray.length * 0.25)];
        double upperQuartile = dataArray[(int) (dataArray.length * 0.75)];
        // 上下四分位点的差值
        double distribution = upperQuartile - lowerQuartile;
        // 上下截断点
        double upperCutoffPoint = upperQuartile + distribution * 1.5;
        double lowerCutoffPoint = lowerQuartile - distribution * 1.5;

        // 校验异常点
        for (int i = 0; i < dataMapList.size(); i++) {
            double currentValue = (Double) dataMapList.get(i).get("value");
            if (lowerCutoffPoint > currentValue || currentValue > upperCutoffPoint) {
                outlierIndexList.add(i);
            }
        }

        detectionResult.setDataMapList(dataMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }
}
