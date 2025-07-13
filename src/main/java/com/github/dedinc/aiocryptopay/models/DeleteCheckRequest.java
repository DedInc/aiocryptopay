package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteCheckRequest {
    @JsonProperty("check_id")
    private long checkId;
}
