package com.hit.cggb.archimedes.entity;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/31,下午2:56
 * @description 趋势预测结果
 */
public class TrendPredictionResult {
    // 异常点探测结果
    private OutlierDetectionResult outlierDetectionResult;
    // 预测值
    private double predictValue;
    // 上浮动预测值
    private double upperFloatPredictValue;
    // 下浮动预测值
    private double lowerFloatPredictValue;

    public OutlierDetectionResult getOutlierDetectionResult() {
        return outlierDetectionResult;
    }

    public void setOutlierDetectionResult(OutlierDetectionResult outlierDetectionResult) {
        this.outlierDetectionResult = outlierDetectionResult;
    }

    public double getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(double predictValue) {
        this.predictValue = predictValue;
    }

    public double getUpperFloatPredictValue() {
        return upperFloatPredictValue;
    }

    public void setUpperFloatPredictValue(double upperFloatPredictValue) {
        this.upperFloatPredictValue = upperFloatPredictValue;
    }

    public double getLowerFloatPredictValue() {
        return lowerFloatPredictValue;
    }

    public void setLowerFloatPredictValue(double lowerFloatPredictValue) {
        this.lowerFloatPredictValue = lowerFloatPredictValue;
    }
}
