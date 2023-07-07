package com.klasha.task.controller;

import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.exception.KlashaException;
import com.klasha.task.services.exchangeRate.ExchangeRateService;
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
    @Cacheable(key = "#country.concat(#targetCurrency).concat(#amount.toString())", value = "exchange")
    public ApiResponse<ExchangeRateResponse> getExchangeDetails(
            @RequestParam String country,
            @RequestParam String targetCurrency,
            @RequestParam BigDecimal amount
    )
            throws KlashaException {
        ExchangeRateRequest exchangeRateRequest = ExchangeRateRequest.builder()
                .country(country)
                .targetCurrency(targetCurrency)
                .amount(amount)
                .build();
        return ApiResponse.ok(exchangeRateService.getExchange(exchangeRateRequest));
    }
}
