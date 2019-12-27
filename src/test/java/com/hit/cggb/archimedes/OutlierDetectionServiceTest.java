package com.hit.cggb.archimedes;

import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.enumtype.AlgorithmTypeEnum;
import com.hit.cggb.archimedes.enumtype.OrderTypeEnum;
import com.hit.cggb.archimedes.service.OutlierDetectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/27,上午11:00
 * @description 异常点探测服务测试类
 */
public class OutlierDetectionServiceTest {
    public static void main(String[] args) {
        // 格式化数据集
        List<Map<String, Object>> dataMapList = new ArrayList<>();

        // 组装参数
        OutlierDetectionParam odParam = new OutlierDetectionParam();
        odParam.setAlgorithmType(AlgorithmTypeEnum.QUARTILE);
        odParam.setOrderType(OrderTypeEnum.DESC);
        odParam.setDataMapList(dataMapList);

        // 实例化异常点探测服务类，调用探测方法
        OutlierDetectionService odService = new OutlierDetectionService();
        OutlierDetectionResult odResult = odService.outlierDetect(odParam);
    }
}
