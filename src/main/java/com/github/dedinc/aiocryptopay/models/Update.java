package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Update {
    @JsonProperty("update_id")
    private long updateId;

    @JsonProperty("update_type")
    private String updateType;

    @JsonProperty("request_date")
    private OffsetDateTime requestDate;

    @JsonProperty("payload")
    private Invoice payload;
}
