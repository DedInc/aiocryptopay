package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dedinc.aiocryptopay.enums.CheckStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetChecksRequest {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("check_ids")
    private String checkIds;

    @JsonProperty("status")
    private CheckStatus status;

    @JsonProperty("offset")
    private Long offset;

    @JsonProperty("count")
    private Long count;
}
