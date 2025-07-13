package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateCheckRequest {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("pin_to_user_id")
    private Long pinToUserId;

    @JsonProperty("pin_to_username")
    private String pinToUsername;
}
