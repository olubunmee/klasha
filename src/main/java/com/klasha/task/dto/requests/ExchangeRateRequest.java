package com.klasha.task.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class ExchangeRateRequest {

    private String country;

    private String targetCurrency;

    private BigDecimal amount;
}
