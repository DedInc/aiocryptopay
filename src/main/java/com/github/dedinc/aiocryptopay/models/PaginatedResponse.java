package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    @JsonProperty("items")
    private List<T> items;
}
