package com.hit.cggb.archimedes.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,下午3:43
 * @description
 */
@JsonIgnoreProperties(value = {"handler"})
public class OutlierDetectionParam {
    @NotBlank(message = "not blank")
    private String indexName;
    @NotBlank(message = "not blank")
    private String indexType;
    @NotBlank(message = "not blank")
    private String startTime;
    @NotBlank(message = "not blank")
    private String endTime;
    @NotBlank(message = "not blank")
    private String algorithmType;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    @Override
    public String toString() {
        return "{\"OutlierDetectionParam\":{"
                + ",\"indexName\":" + indexName
                + ",\"indexType\":" + indexType
                + ",\"startTime\":" + startTime
                + ",\"endTime\":" + endTime
                + ",\"algorithmType\":\"" + algorithmType + "\""
                + "}}";
    }
}
