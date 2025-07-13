package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoPayResponse<T> {
    @JsonProperty("ok")
    private boolean ok;

    @JsonProperty("result")
    private T result;

    @JsonProperty("error")
    private CryptoPayError error;
}
