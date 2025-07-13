package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dedinc.aiocryptopay.enums.CheckStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Check {
    @JsonProperty("check_id")
    private long checkId;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("bot_check_url")
    private String botCheckUrl;

    @JsonProperty("status")
    private CheckStatus status;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("activated_at")
    private OffsetDateTime activatedAt;
}
