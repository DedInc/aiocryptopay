package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("available")
    private BigDecimal available;

    @JsonProperty("onhold")
    private BigDecimal onhold;
}
