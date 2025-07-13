package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Transfer {
    @JsonProperty("transfer_id")
    private long transferId;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("completed_at")
    private OffsetDateTime completedAt;

    @JsonProperty("comment")
    private String comment;
}
