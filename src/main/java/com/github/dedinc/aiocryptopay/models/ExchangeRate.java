package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRate {
    @JsonProperty("is_valid")
    private boolean isValid;

    @JsonProperty("is_crypto")
    private boolean isCrypto;

    @JsonProperty("is_fiat")
    private boolean isFiat;

    @JsonProperty("source")
    private String source;

    @JsonProperty("target")
    private String target;

    @JsonProperty("rate")
    private BigDecimal rate;
}
