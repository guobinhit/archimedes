package com.hit.cggb.archimedes.entity;

import com.hit.cggb.archimedes.enumtype.PredictionAlgorithmTypeEnum;
import com.sun.istack.internal.NotNull;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/31,下午2:56
 * @description 趋势预测参数
 */
public class TrendPredictionParam {
    // 异常点探测参数，必填项
    @NotNull
    private OutlierDetectionParam outlierDetectionParam;
    // 趋势预测算法类型，必填项
    @NotNull
    private PredictionAlgorithmTypeEnum predictionAlgorithmType;
    // 计算周期
    private int calculatePeriod;
    // 预测值上浮动系数
    private double upperFloatCoefficient;
    // 预测值下浮动系数
    private double lowerFloatCoefficient;

    public OutlierDetectionParam getOutlierDetectionParam() {
        return outlierDetectionParam;
    }

    public void setOutlierDetectionParam(OutlierDetectionParam outlierDetectionParam) {
        this.outlierDetectionParam = outlierDetectionParam;
    }

    public PredictionAlgorithmTypeEnum getPredictionAlgorithmType() {
        return predictionAlgorithmType;
    }

    public void setPredictionAlgorithmType(PredictionAlgorithmTypeEnum predictionAlgorithmType) {
        this.predictionAlgorithmType = predictionAlgorithmType;
    }

    public int getCalculatePeriod() {
        return calculatePeriod;
    }

    public void setCalculatePeriod(int calculatePeriod) {
        this.calculatePeriod = calculatePeriod;
    }

    public double getUpperFloatCoefficient() {
        return upperFloatCoefficient;
    }

    public void setUpperFloatCoefficient(double upperFloatCoefficient) {
        this.upperFloatCoefficient = upperFloatCoefficient;
    }

    public double getLowerFloatCoefficient() {
        return lowerFloatCoefficient;
    }

    public void setLowerFloatCoefficient(double lowerFloatCoefficient) {
        this.lowerFloatCoefficient = lowerFloatCoefficient;
    }
}
