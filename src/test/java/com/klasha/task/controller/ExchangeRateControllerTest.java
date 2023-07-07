package com.klasha.task.controller;

import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.services.exchangeRate.ExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExchangeRateControllerTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ExchangeRateController exchangeRateController = new ExchangeRateController(exchangeRateService);
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeRateController).build();
    }

    @Test
    public void testGetExchangeDetails() throws Exception {
        String country = "TestCountry";
        String targetCurrency = "TargetCurrency";
        BigDecimal amount = BigDecimal.TEN;
        ExchangeRateRequest exchangeRateRequest = ExchangeRateRequest.builder()
                .country(country)
                .targetCurrency(targetCurrency)
                .amount(amount)
                .build();
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setAmount(BigDecimal.valueOf(15.0));

        when(exchangeRateService.getExchange(exchangeRateRequest)).thenReturn(exchangeRateResponse);

        mockMvc.perform(get("/api/v1/exchange")
                        .param("country", country)
                        .param("targetCurrency", targetCurrency)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());

    }
}
