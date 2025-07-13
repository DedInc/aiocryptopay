package com.github.dedinc.aiocryptopay.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CurrencyType {
    @JsonProperty("crypto")
    CRYPTO,
    @JsonProperty("fiat")
    FIAT
}
