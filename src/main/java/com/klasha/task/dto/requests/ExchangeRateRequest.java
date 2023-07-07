package com.klasha.task.dto.requests;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest {

    private String country;

    private String targetCurrency;

    private BigDecimal amount;
}
