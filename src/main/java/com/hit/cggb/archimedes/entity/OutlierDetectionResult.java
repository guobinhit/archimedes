package com.hit.cggb.archimedes.entity;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:08
 * @description 异常点探测
 */
public class OutlierDetectionResult {
    // 原始数据列表
    private List<Map<String, Object>> dataMapList;
    // 异常点索引列表
    private List<Integer> outlierIndexList;

    public List<Map<String, Object>> getDataMapList() {
        return dataMapList;
    }

    public void setDataMapList(List<Map<String, Object>> dataMapList) {
        this.dataMapList = dataMapList;
    }

    public List<Integer> getOutlierIndexList() {
        return outlierIndexList;
    }

    public void setOutlierIndexList(List<Integer> outlierIndexList) {
        this.outlierIndexList = outlierIndexList;
    }
}
