package com.klasha.task.services.exchangeRate;

import com.klasha.task.api.ExchangeApi;
import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CurrencyResponse;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.exception.KlashaException;
import com.klasha.task.utils.CsvUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExchangeRateServiceImplTest {

    @Mock
    private CsvUtil csvUtil;

    @Mock
    private ExchangeApi exchangeApi;

    private ExchangeRateServiceImpl exchangeRateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        exchangeRateService = new ExchangeRateServiceImpl(csvUtil, exchangeApi);
    }

    @Test
    public void testGetExchange() throws KlashaException {

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest("TestCountry", "TargetCurrency", BigDecimal.TEN);
        CurrencyResponse currencyResponse = new CurrencyResponse("Ger", "ISO2", "ISO3","Currency");
        Double rate = 1.5;

        when(exchangeApi.getCurrencyData(exchangeRateRequest.getCountry())).thenReturn(ApiResponse.ok(currencyResponse));

        String pair = currencyResponse.getCurrency().concat("-").concat(exchangeRateRequest.getTargetCurrency());
        when(csvUtil.getRate(pair.toUpperCase())).thenReturn(rate);

        ExchangeRateResponse result = exchangeRateService.getExchange(exchangeRateRequest);

        verify(exchangeApi, times(1)).getCurrencyData(exchangeRateRequest.getCountry());
        verify(csvUtil, times(1)).getRate(pair.toUpperCase());

        assertEquals(currencyResponse.getCurrency(), result.getSourceCurrency());
        assertEquals(exchangeRateRequest.getTargetCurrency(), result.getTargetCurrency());
        assertEquals(BigDecimal.valueOf(15.0).setScale(2), result.getAmount());
    }
}
