package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    @JsonProperty("is_blockchain")
    private boolean isBlockchain;

    @JsonProperty("is_stablecoin")
    private boolean isStablecoin;

    @JsonProperty("is_fiat")
    private boolean isFiat;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("url")
    private String url;

    @JsonProperty("decimals")
    private int decimals;
}
