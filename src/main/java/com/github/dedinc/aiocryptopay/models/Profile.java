package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Profile {
    @JsonProperty("app_id")
    private long appId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("payment_processing_bot_username")
    private String paymentProcessingBotUsername;
}
