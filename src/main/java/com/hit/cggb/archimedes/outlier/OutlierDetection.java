package com.hit.cggb.archimedes.outlier;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/25,下午3:06
 * @description 异常点探索接口
 */
public interface OutlierDetection {
    /**
     * 异常点探测方法
     *
     * @param dataMapList 数据列表
     * @return 探测结果
     */
    OutlierDetectionResult detect(List<Map<String, Object>> dataMapList);
}
