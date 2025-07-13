# CryptoPay Java SDK

Asynchronous Java SDK for the [@CryptoBot](https://t.me/CryptoBot) API.

**Documentation:** https://help.crypt.bot/crypto-pay-api

- **MainNet:** [@CryptoBot](http://t.me/CryptoBot)
- **TestNet:** [@CryptoTestnetBot](http://t.me/CryptoTestnetBot)

## Installation

### JitPack

To use this library with JitPack, add the following to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.DedInc:aiocryptopay:1.0")
}
```

Or for Maven:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.DeedInc</groupId>
    <artifactId>aiocryptopay</artifactId>
    <version>1.0</version>
</dependency>
```

## Basic Usage

### Initialization

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import okhttp3.OkHttpClient;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://testnet-pay.crypt.bot"; // or https://pay.crypt.bot for mainnet
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);

        // ...
    }
}
```

### Get Basic Information

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import com.github.dedinc.aiocryptopay.models.*;
import okhttp3.OkHttpClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://pay.crypt.bot"; // or https://testnet-pay.crypt.bot for testnet
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);

        // Get profile information
        CompletableFuture<Profile> profileFuture = cryptoPay.getMe();
        Profile profile = profileFuture.join();
        System.out.println("Profile: " + profile);

        // Get balance
        CompletableFuture<List<Balance>> balanceFuture = cryptoPay.getBalance();
        List<Balance> balances = balanceFuture.join();
        System.out.println("Balances: " + balances);

        // Get exchange rates
        CompletableFuture<List<ExchangeRate>> ratesFuture = cryptoPay.getExchangeRates();
        List<ExchangeRate> rates = ratesFuture.join();
        System.out.println("Exchange rates count: " + rates.size());

        // Get currencies
        CompletableFuture<List<Currency>> currenciesFuture = cryptoPay.getCurrencies();
        List<Currency> currencies = currenciesFuture.join();
        System.out.println("Currencies count: " + currencies.size());

        // Get stats
        GetStatsRequest statsRequest = GetStatsRequest.builder().build();
        CompletableFuture<AppStats> statsFuture = cryptoPay.getStats(statsRequest);
        AppStats stats = statsFuture.join();
        System.out.println("Stats: " + stats);
    }
}
```

### Invoices

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import com.github.dedinc.aiocryptopay.models.*;
import okhttp3.OkHttpClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://pay.crypt.bot";
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);

        // Create Invoice
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .asset("TON")
                .amount(new BigDecimal("1.5"))
                .build();
        CompletableFuture<Invoice> invoiceFuture = cryptoPay.createInvoice(createInvoiceRequest);
        Invoice invoice = invoiceFuture.join();
        System.out.println("Invoice created: " + invoice);
        System.out.println("Bot Invoice URL: " + invoice.getBotInvoiceUrl());
        System.out.println("Web App URL: " + invoice.getWebAppInvoiceUrl());
        System.out.println("Mini App URL: " + invoice.getMiniAppInvoiceUrl());

        // Get Invoices
        GetInvoicesRequest getInvoicesRequest = GetInvoicesRequest.builder()
                .invoiceIds(String.valueOf(invoice.getInvoiceId()))
                .build();
        CompletableFuture<List<Invoice>> invoicesFuture = cryptoPay.getInvoices(getInvoicesRequest);
        List<Invoice> invoices = invoicesFuture.join();
        System.out.println("Retrieved invoices: " + invoices.size());
        if (!invoices.isEmpty()) {
            System.out.println("Invoice status: " + invoices.get(0).getStatus());
        }

        // Delete Invoice
        DeleteInvoiceRequest deleteInvoiceRequest = DeleteInvoiceRequest.builder()
                .invoiceId(invoice.getInvoiceId())
                .build();
        CompletableFuture<Boolean> deletedFuture = cryptoPay.deleteInvoice(deleteInvoiceRequest);
        System.out.println("Invoice deleted: " + deletedFuture.join());
    }
}
```

### Checks

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import com.github.dedinc.aiocryptopay.models.*;
import okhttp3.OkHttpClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://pay.crypt.bot";
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);

        // Create Check
        CreateCheckRequest createCheckRequest = CreateCheckRequest.builder()
                .asset("USDT")
                .amount(new BigDecimal("1.0"))
                .build();
        CompletableFuture<Check> checkFuture = cryptoPay.createCheck(createCheckRequest);
        Check check = checkFuture.join();
        System.out.println("Check created: " + check);

        // Get Checks
        GetChecksRequest getChecksRequest = GetChecksRequest.builder()
                .checkIds(String.valueOf(check.getCheckId()))
                .build();
        CompletableFuture<List<Check>> checksFuture = cryptoPay.getChecks(getChecksRequest);
        List<Check> checks = checksFuture.join();
        System.out.println("Retrieved checks: " + checks.size());

        // Delete Check
        DeleteCheckRequest deleteCheckRequest = DeleteCheckRequest.builder()
                .checkId(check.getCheckId())
                .build();
        CompletableFuture<Boolean> deletedCheckFuture = cryptoPay.deleteCheck(deleteCheckRequest);
        System.out.println("Check deleted: " + deletedCheckFuture.join());
    }
}
```

### Transfers

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import com.github.dedinc.aiocryptopay.models.*;
import okhttp3.OkHttpClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://pay.crypt.bot";
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);

        // Create Transfer
        TransferRequest transferRequest = TransferRequest.builder()
                .userId(123456789L)
                .asset("TON")
                .amount(new BigDecimal("0.1"))
                .spendId("unique-spend-id-123")
                .comment("Test transfer")
                .build();
        CompletableFuture<Transfer> transferFuture = cryptoPay.transfer(transferRequest);
        Transfer transfer = transferFuture.join();
        System.out.println("Transfer created: " + transfer);

        // Get Transfers
        GetTransfersRequest getTransfersRequest = GetTransfersRequest.builder().build();
        CompletableFuture<List<Transfer>> transfersFuture = cryptoPay.getTransfers(getTransfersRequest);
        List<Transfer> transfers = transfersFuture.join();
        System.out.println("Transfers count: " + transfers.size());
    }
}
```

### Webhook Handling

To handle webhook updates, you can implement a simple web server (e.g., using SparkJava or Spring Boot) and register a handler with the `CryptoPay` client.

```java
import com.github.dedinc.aiocryptopay.CryptoPay;
import com.github.dedinc.aiocryptopay.models.Update;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        String token = "YOUR_TOKEN";
        String host = "https://pay.crypt.bot";
        OkHttpClient client = new OkHttpClient();
        CryptoPay cryptoPay = new CryptoPay(token, client, host);
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        cryptoPay.addUpdateHandler(update -> {
            System.out.println("Received update: " + update);
            // Process the update here
        });

        port(3001);
        post("/crypto-secret-path", (req, res) -> {
            String signature = req.headers("Crypto-Pay-Api-Signature");
            if (cryptoPay.checkSignature(req.body(), signature)) {
                Update update = objectMapper.readValue(req.body(), Update.class);
                cryptoPay.handleUpdate(update);
                res.status(200);
                return "OK";
            } else {
                res.status(400);
                return "Invalid signature";
            }
        });
    }
}
