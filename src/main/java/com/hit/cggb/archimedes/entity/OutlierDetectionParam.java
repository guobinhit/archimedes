package com.hit.cggb.archimedes.entity;

import com.hit.cggb.archimedes.enumtype.DetectionAlgorithmTypeEnum;
import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:43
 * @description 异常点探测参数类
 */
public class OutlierDetectionParam {
    // 异常点探测算法类型，必填项
    @NotNull
    private DetectionAlgorithmTypeEnum detectionAlgorithmType;
    // 排序类型，可选项
    private OrderTypeEnum orderType;
    // 数据列表，必填项
    @NotNull
    private List<Map<String, Object>> dataMapList;

    public DetectionAlgorithmTypeEnum getDetectionAlgorithmType() {
        return detectionAlgorithmType;
    }

    public void setDetectionAlgorithmType(DetectionAlgorithmTypeEnum detectionAlgorithmType) {
        this.detectionAlgorithmType = detectionAlgorithmType;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public List<Map<String, Object>> getDataMapList() {
        return dataMapList;
    }

    public void setDataMapList(List<Map<String, Object>> dataMapList) {
        this.dataMapList = dataMapList;
    }
}
