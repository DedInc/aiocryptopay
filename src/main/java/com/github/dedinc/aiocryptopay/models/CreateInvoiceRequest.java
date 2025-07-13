package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dedinc.aiocryptopay.enums.CurrencyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateInvoiceRequest {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("description")
    private String description;

    @JsonProperty("hidden_message")
    private String hiddenMessage;

    @JsonProperty("paid_btn_name")
    private String paidBtnName;

    @JsonProperty("paid_btn_url")
    private String paidBtnUrl;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("allow_comments")
    private Boolean allowComments;

    @JsonProperty("allow_anonymous")
    private Boolean allowAnonymous;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("fiat")
    private String fiat;

    @JsonProperty("currency_type")
    private CurrencyType currencyType;

    @JsonProperty("accepted_assets")
    private List<String> acceptedAssets;

    @JsonProperty("swap_to")
    private String swapTo;
}
