package com.klasha.task.services.exchangeRate;

import com.klasha.task.dto.responses.ExchangeRateResponse;
import com.klasha.task.dto.requests.ExchangeRateRequest;
import com.klasha.task.exception.KlashaException;

public interface ExchangeRateService {
      ExchangeRateResponse getExchange(ExchangeRateRequest exchangeRateRequest) throws KlashaException;
}
