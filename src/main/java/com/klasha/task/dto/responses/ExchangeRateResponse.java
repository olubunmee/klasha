package com.klasha.task.dto.responses;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRateResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
}
