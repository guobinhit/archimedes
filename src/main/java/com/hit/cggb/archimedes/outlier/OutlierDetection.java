package com.hit.cggb.archimedes.outlier;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;

import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:06
 * @description
 */
public interface OutlierDetection {
    /**
     * 异常点探测方法
     *
     * @param timeSeriesMapList 数据列表
     * @return 探测结果
     */
    OutlierDetectionResult detect(List<Map<String, Object>> timeSeriesMapList);
}
