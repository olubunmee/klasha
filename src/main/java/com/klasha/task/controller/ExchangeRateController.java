package com.klasha.task.controller;

import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.exception.KlashaException;
import com.klasha.task.services.exchangeRate.ExchangeRateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;


    @GetMapping("exchange")
    @Cacheable(key = "#sourceCountry.concat(#targetCurrency).concat(#amount.toString())", value = "exchange")
    public ApiResponse<ExchangeRateResponse> getExchangeDetails(
            @RequestParam String sourceCountry,
            @RequestParam String targetCurrency,
            @RequestParam BigDecimal amount
    )
            throws KlashaException {
        ExchangeRateRequest exchangeRateRequest = ExchangeRateRequest.builder()
                .sourceCountry(sourceCountry)
                .targetCurrency(targetCurrency)
                .amount(amount)
                .build();
        return ApiResponse.ok(exchangeRateService.getExchange(exchangeRateRequest));
    }
}
