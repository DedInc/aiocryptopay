package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class AppStats {
    @JsonProperty("volume")
    private BigDecimal volume;

    @JsonProperty("conversion")
    private BigDecimal conversion;

    @JsonProperty("unique_users_count")
    private int uniqueUsersCount;

    @JsonProperty("created_invoice_count")
    private int createdInvoiceCount;

    @JsonProperty("paid_invoice_count")
    private int paidInvoiceCount;

    @JsonProperty("start_at")
    private OffsetDateTime startAt;

    @JsonProperty("end_at")
    private OffsetDateTime endAt;
}
