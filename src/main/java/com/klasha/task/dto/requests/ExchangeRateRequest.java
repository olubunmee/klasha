package com.klasha.task.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class ExchangeRateRequest {
    @NotBlank(message = "{sourceCountry.not_blank}")
    private String sourceCountry;
    @NotBlank(message = "{targetCurrency.not_blank}")
    private String targetCurrency;
    @Positive(message = "{amount.invalid}")
    private BigDecimal amount;
}
