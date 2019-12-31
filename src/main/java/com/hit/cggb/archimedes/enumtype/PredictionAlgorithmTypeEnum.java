package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/31,下午3:05
 * @description 趋势预测算法类型枚举
 */
public enum PredictionAlgorithmTypeEnum {
    FIRST_EXPONENTIAL_SMOOTHING("一次指数平滑法"),
    SECOND_EXPONENTIAL_SMOOTHING("二次指数平滑法"),
    THIRD_EXPONENTIAL_SMOOTHING("三次指数平滑法，Holt-Winters法");

    PredictionAlgorithmTypeEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
