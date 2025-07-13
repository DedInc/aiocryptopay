package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransferRequest {
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("spend_id")
    private String spendId;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("disable_send_notification")
    private Boolean disableSendNotification;
}
