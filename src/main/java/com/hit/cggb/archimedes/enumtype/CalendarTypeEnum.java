package com.hit.cggb.archimedes.enumtype;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:31
 * @description 时间类型枚举
 */
public enum CalendarTypeEnum {
    YEAR("年", 1),
    MONTH("月", 2),
    DATE("日", 5),
    HOUR("时", 10),
    MINUTE("分", 12),
    SECOND("秒", 13),
    MILLISECOND("毫秒", 14);

    CalendarTypeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    private String desc;
    private int value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
