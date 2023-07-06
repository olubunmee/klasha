package com.klasha.task.services.exchangeRate;

import com.klasha.task.api.ExchangeApi;
import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.dto.responses.CurrencyResponse;
import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.exception.KlashaException;
import com.klasha.task.utils.CsvUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.UP;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final CsvUtil csvUtil;

    private final ExchangeApi exchangeApi;

    @Override
    public ExchangeRateResponse getExchange(ExchangeRateRequest exchangeRateRequest) throws KlashaException {
        CurrencyResponse currencyResponse = exchangeApi.getCurrencyData(exchangeRateRequest.getSourceCountry()).getData();

        String pair = currencyResponse.getCurrency().concat("-").concat(exchangeRateRequest.getTargetCurrency());
        Double rate = csvUtil.getRate(pair.toUpperCase());
        if (rate == null) throw new KlashaException("Exchange rate not available for " + pair);

        BigDecimal amount = exchangeRateRequest.getAmount().multiply(valueOf(rate));
        BigDecimal roundedAmount = amount.setScale(2, UP);

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setSourceCurrency(currencyResponse.getCurrency());
        exchangeRateResponse.setTargetCurrency(exchangeRateRequest.getTargetCurrency());
        exchangeRateResponse.setAmount(roundedAmount);
        return exchangeRateResponse;
    }
}
