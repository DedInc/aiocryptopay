package com.github.dedinc.aiocryptopay.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dedinc.aiocryptopay.enums.CurrencyType;
import com.github.dedinc.aiocryptopay.enums.InvoiceStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class Invoice {
    @JsonProperty("invoice_id")
    private long invoiceId;

    @JsonProperty("status")
    private InvoiceStatus status;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("bot_invoice_url")
    private String botInvoiceUrl;

    @JsonProperty("web_app_invoice_url")
    private String webAppInvoiceUrl;

    @JsonProperty("mini_app_invoice_url")
    private String miniAppInvoiceUrl;

    @JsonProperty("pay_url")
    private String payUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("allow_comments")
    private boolean allowComments;

    @JsonProperty("allow_anonymous")
    private boolean allowAnonymous;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("paid_at")
    private OffsetDateTime paidAt;

    @JsonProperty("paid_anonymously")
    private Boolean paidAnonymously;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("hidden_message")
    private String hiddenMessage;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("paid_btn_name")
    private String paidBtnName;

    @JsonProperty("paid_btn_url")
    private String paidBtnUrl;

    @JsonProperty("currency_type")
    private CurrencyType currencyType;

    @JsonProperty("fiat")
    private String fiat;

    @JsonProperty("paid_asset")
    private String paidAsset;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @JsonProperty("paid_usd_rate")
    private BigDecimal paidUsdRate;

    @JsonProperty("paid_fiat_rate")
    private BigDecimal paidFiatRate;

    @JsonProperty("fee_asset")
    private String feeAsset;

    @JsonProperty("fee_amount")
    private BigDecimal feeAmount;

    @JsonProperty("fee_in_usd")
    private BigDecimal feeInUsd;

    @JsonProperty("accepted_assets")
    private List<String> acceptedAssets;

    @JsonProperty("swap_to")
    private String swapTo;

    @JsonProperty("is_swapped")
    private Boolean isSwapped;

    @JsonProperty("swapped_uid")
    private String swappedUid;

    @JsonProperty("swapped_to")
    private String swappedTo;

    @JsonProperty("swapped_rate")
    private BigDecimal swappedRate;

    @JsonProperty("swapped_output")
    private BigDecimal swappedOutput;

    @JsonProperty("swapped_usd_rate")
    private BigDecimal swappedUsdRate;

    @JsonProperty("swapped_usd_amount")
    private BigDecimal swappedUsdAmount;

    @JsonProperty("is_confirmed")
    private boolean isConfirmed;
}
