package com.hit.cggb.archimedes.entity;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:43
 * @description 异常点探测参数类
 */
public class OutlierDetectionParam {

    // 算法类型
    private String algorithmType;
    // 排序类型
    private String orderType;
    // 数据列表
    private List<Map<String, Object>> dataMapList;

    public String getAlgorithmType() {
        return algorithmType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public List<Map<String, Object>> getDataMapList() {
        return dataMapList;
    }

    public void setDataMapList(List<Map<String, Object>> dataMapList) {
        this.dataMapList = dataMapList;
    }
}
