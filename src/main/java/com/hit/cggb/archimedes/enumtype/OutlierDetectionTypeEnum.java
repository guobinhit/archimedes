package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:10
 * @description 异常点探测算法枚举
 */
public enum OutlierDetectionTypeEnum {
    QUARTILE("四分位"),
    SIGMA3("3-SIGMA"),
    ZSCORE("Z-SCORE"),
    DBSCAN("DBSCAN"),
    STCR("短期环比"),
    LOF("LOF");

    OutlierDetectionTypeEnum(String desc) {
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
