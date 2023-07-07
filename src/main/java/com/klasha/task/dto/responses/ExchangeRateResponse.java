package com.klasha.task.dto.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRateResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
    private String message;
}
