package com.github.dedinc.aiocryptopay.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InvoiceStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("paid")
    PAID,
    @JsonProperty("expired")
    EXPIRED
}
