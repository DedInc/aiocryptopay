package com.github.dedinc.aiocryptopay.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CheckStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("activated")
    ACTIVATED
}
