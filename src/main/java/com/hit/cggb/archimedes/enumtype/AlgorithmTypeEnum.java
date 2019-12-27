package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:10
 * @description 算法类型枚举
 */
public enum AlgorithmTypeEnum {
    QUARTILE("四分位"),
    SIGMA3("3-SIGMA"),
    ZSCORE("Z-SCORE"),
    DBSCAN("DBSCAN"),
    STCR("短期环比"),
    LOF("LOF");

    AlgorithmTypeEnum(String desc) {
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
