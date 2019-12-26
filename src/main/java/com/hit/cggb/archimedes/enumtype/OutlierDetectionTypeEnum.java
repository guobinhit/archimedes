package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:10
 * @description
 */
public enum OutlierDetectionTypeEnum {
    QUARTILE("quartile", "四分位"),
    SIGMA3("sigma3", "3-SIGMA"),
    ZSCORE("zscore", "Z-SCORE"),
    DBSCAN("dbscan", "DBSCAN"),
    STCR("stcr", "短期环比"),
    LOF("lof", "LOF");

    OutlierDetectionTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
