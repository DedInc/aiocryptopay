package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoPayError {
    @JsonProperty("code")
    private int code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
