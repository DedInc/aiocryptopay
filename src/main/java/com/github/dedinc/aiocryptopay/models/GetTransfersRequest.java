package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTransfersRequest {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("transfer_ids")
    private String transferIds;

    @JsonProperty("offset")
    private Long offset;

    @JsonProperty("count")
    private Long count;
}
