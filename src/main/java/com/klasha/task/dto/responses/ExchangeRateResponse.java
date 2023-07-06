package com.klasha.task.dto.responses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRateResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
}
