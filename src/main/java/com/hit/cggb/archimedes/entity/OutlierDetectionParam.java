package com.hit.cggb.archimedes.entity;

import com.hit.cggb.archimedes.enumtype.AlgorithmTypeEnum;
import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:43
 * @description 异常点探测参数类
 */
public class OutlierDetectionParam {

    // 算法类型，必传参数
    private AlgorithmTypeEnum algorithmType;
    // 排序类型，可选参数
    private OrderTypeEnum orderType;
    // 数据列表，必传参数
    private List<Map<String, Object>> dataMapList;

    public AlgorithmTypeEnum getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(AlgorithmTypeEnum algorithmType) {
        this.algorithmType = algorithmType;
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
