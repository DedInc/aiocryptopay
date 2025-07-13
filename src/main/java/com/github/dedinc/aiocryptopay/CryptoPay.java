package com.github.dedinc.aiocryptopay;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.dedinc.aiocryptopay.exceptions.CryptoPayException;
import com.github.dedinc.aiocryptopay.models.*;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CryptoPay {

    private final String token;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String host;
    private final List<Consumer<Update>> updateHandlers = new ArrayList<>();

    public CryptoPay(String token, OkHttpClient client, String host) {
        this.token = token;
        this.client = client;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.host = host;
    }

    public <T> CompletableFuture<T> makeRequest(String method, String path, Class<T> responseClass) {
        return makeRequest(method, path, null, responseClass);
    }

    public <T> CompletableFuture<T> makeRequest(String method, String path, Object params, Class<T> responseClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructType(responseClass);
        return makeRequest(method, path, params, javaType);
    }

    public <T> CompletableFuture<T> makeRequest(String method, String path, com.fasterxml.jackson.databind.JavaType responseType) {
        return makeRequest(method, path, null, responseType);
    }

    public <T> CompletableFuture<T> makeRequest(String method, String path, Object params, com.fasterxml.jackson.databind.JavaType responseType) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(host + path)).newBuilder();
                Request.Builder requestBuilder = new Request.Builder()
                        .addHeader("Crypto-Pay-API-Token", token);

                if ("POST".equalsIgnoreCase(method)) {
                    String json = objectMapper.writeValueAsString(params);
                    requestBuilder.post(RequestBody.create(json, MediaType.get("application/json")));
                } else {
                    if (params != null) {
                        objectMapper.convertValue(params, new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, Object>>() {
                        }).forEach((key, value) -> {
                            if (value != null) {
                                urlBuilder.addQueryParameter(key, value.toString());
                            }
                        });
                    }
                }

                Request request = requestBuilder.url(urlBuilder.build()).build();

                try (Response response = client.newCall(request).execute()) {
                    String responseBody = Objects.requireNonNull(response.body()).string();
                    JavaType wrapperType = objectMapper.getTypeFactory().constructParametricType(CryptoPayResponse.class, responseType);
                    CryptoPayResponse<T> cryptoPayResponse = objectMapper.readValue(responseBody, wrapperType);

                    if (!cryptoPayResponse.isOk()) {
                        String errorMessage = "Request failed: " + response.code();
                        if (cryptoPayResponse.getError() != null) {
                            errorMessage += " - " + cryptoPayResponse.getError().getName();
                            if (cryptoPayResponse.getError().getDescription() != null) {
                                errorMessage += ": " + cryptoPayResponse.getError().getDescription();
                            }
                            errorMessage += " (code: " + cryptoPayResponse.getError().getCode() + ")";
                        } else {
                            errorMessage += " " + responseBody;
                        }
                        throw new CryptoPayException(errorMessage);
                    }
                    return cryptoPayResponse.getResult();
                }
            } catch (IOException e) {
                throw new CryptoPayException("Request execution failed", e);
            }
        });
    }

    public CompletableFuture<Profile> getMe() {
        return makeRequest("GET", "/api/getMe", Profile.class);
    }

    public CompletableFuture<List<Balance>> getBalance() {
        return makeRequest("GET", "/api/getBalance",
                objectMapper.getTypeFactory().constructCollectionType(List.class, Balance.class));
    }

    public CompletableFuture<List<ExchangeRate>> getExchangeRates() {
        return makeRequest("GET", "/api/getExchangeRates",
                objectMapper.getTypeFactory().constructCollectionType(List.class, ExchangeRate.class));
    }

    public CompletableFuture<List<Currency>> getCurrencies() {
        return makeRequest("GET", "/api/getCurrencies",
                objectMapper.getTypeFactory().constructCollectionType(List.class, Currency.class));
    }

    public CompletableFuture<Invoice> createInvoice(CreateInvoiceRequest payload) {
        return makeRequest("POST", "/api/createInvoice", payload, Invoice.class);
    }

    public CompletableFuture<List<Invoice>> getInvoices(GetInvoicesRequest payload) {
        return makeRequest("GET", "/api/getInvoices", payload,
                objectMapper.getTypeFactory().constructParametricType(PaginatedResponse.class, Invoice.class))
                .thenApply(response -> ((PaginatedResponse<Invoice>) response).getItems());
    }

    public CompletableFuture<Transfer> transfer(TransferRequest payload) {
        return makeRequest("POST", "/api/transfer", payload, Transfer.class);
    }

    public CompletableFuture<List<Transfer>> getTransfers(GetTransfersRequest payload) {
        return makeRequest("GET", "/api/getTransfers", payload,
                objectMapper.getTypeFactory().constructParametricType(PaginatedResponse.class, Transfer.class))
                .thenApply(response -> ((PaginatedResponse<Transfer>) response).getItems());
    }

    public CompletableFuture<Check> createCheck(CreateCheckRequest payload) {
        return makeRequest("POST", "/api/createCheck", payload, Check.class);
    }

    public CompletableFuture<List<Check>> getChecks(GetChecksRequest payload) {
        return makeRequest("GET", "/api/getChecks", payload,
                objectMapper.getTypeFactory().constructParametricType(PaginatedResponse.class, Check.class))
                .thenApply(response -> ((PaginatedResponse<Check>) response).getItems());
    }

    public CompletableFuture<Boolean> deleteInvoice(DeleteInvoiceRequest payload) {
        return makeRequest("POST", "/api/deleteInvoice", payload, Boolean.class);
    }

    public CompletableFuture<Boolean> deleteCheck(DeleteCheckRequest payload) {
        return makeRequest("POST", "/api/deleteCheck", payload, Boolean.class);
    }

    public CompletableFuture<AppStats> getStats(GetStatsRequest payload) {
        return makeRequest("GET", "/api/getStats", payload, AppStats.class);
    }

    public boolean checkSignature(String body, String signature) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(DigestUtils.sha256(token), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hash = Hex.encodeHexString(sha256_HMAC.doFinal(body.getBytes()));
            return hash.equals(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoPayException("Signature check failed", e);
        }
    }

    public void addUpdateHandler(Consumer<Update> handler) {
        updateHandlers.add(handler);
    }

    public void handleUpdate(Update update) {
        updateHandlers.forEach(handler -> handler.accept(update));
    }

    public CompletableFuture<BigDecimal> getAmountByFiat(BigDecimal fiatAmount, String asset, String target) {
        return getExchangeRates().thenApply(exchangeRates -> {
            for (ExchangeRate rate : exchangeRates) {
                if (rate.getSource().equals(asset) && rate.getTarget().equals(target)) {
                    return fiatAmount.divide(rate.getRate(), 8, RoundingMode.HALF_UP);
                }
            }
            throw new CryptoPayException("Rate not found for " + asset + " to " + target);
        });
    }
}
