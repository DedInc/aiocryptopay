package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetInvoicesRequest {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("invoice_ids")
    private String invoiceIds;

    @JsonProperty("status")
    private String status;

    @JsonProperty("offset")
    private Long offset;

    @JsonProperty("count")
    private Long count;
}
