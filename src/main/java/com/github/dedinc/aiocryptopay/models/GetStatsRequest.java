package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class GetStatsRequest {
    @JsonProperty("start_at")
    private OffsetDateTime startAt;

    @JsonProperty("end_at")
    private OffsetDateTime endAt;
}
