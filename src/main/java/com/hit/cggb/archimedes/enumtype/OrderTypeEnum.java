package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/27,上午11:27
 * @description 排序类型枚举
 */
public enum OrderTypeEnum {
    ASC("升序"),
    DESC("降序");

    OrderTypeEnum(String desc) {
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
