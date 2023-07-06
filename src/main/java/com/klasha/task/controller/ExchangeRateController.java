package com.klasha.task.controller;

import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.exception.KlashaException;
import com.klasha.task.services.exchangeRate.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;


    @GetMapping("exchange")
    public ApiResponse<ExchangeRateResponse> getExchangeDetails(@RequestBody @Valid ExchangeRateRequest exchangeRateRequest)
            throws KlashaException {
        return ApiResponse.ok(exchangeRateService.getExchange(exchangeRateRequest));
    }
}
