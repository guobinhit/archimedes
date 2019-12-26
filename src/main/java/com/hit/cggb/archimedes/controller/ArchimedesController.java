package com.hit.cggb.archimedes.controller;

import com.hit.cggb.archimedes.entity.OutlierDetectionParam;
import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.service.OutlierDetectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:12
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/archimedes")
@Api(tags = "阿基米德控制器")
public class ArchimedesController {
    private final OutlierDetectionService outlierDetectionService;

    public ArchimedesController(OutlierDetectionService outlierDetectionService) {
        this.outlierDetectionService = outlierDetectionService;
    }

    @ApiOperation(value = "异常点探测")
    @PostMapping(value = "/outlier/detect")
    public OutlierDetectionResult outlierDetect(@RequestBody @Validated OutlierDetectionParam detectionParam) {
        return outlierDetectionService.outlierDetect(detectionParam);
    }
}
