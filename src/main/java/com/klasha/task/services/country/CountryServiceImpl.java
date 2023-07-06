package com.klasha.task.services.country;


import com.klasha.task.api.CountryApi;
import com.klasha.task.api.ExchangeApi;
import com.klasha.task.dto.PopulationCount;
import com.klasha.task.dto.responses.CountryResponse;
import com.klasha.task.dto.responses.CurrencyResponse;
import com.klasha.task.dto.responses.PopulationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryApi countryApi;
    private final ExchangeApi exchangeApi;


    @Override
    public CountryResponse getCountryDetail(String country) {

        CurrencyResponse currencyResponse = exchangeApi.getCurrencyData(country).getData();
        PopulationResponse populationResponse = countryApi.getPopulationData(country).getData();
        PopulationCount populationCount = populationResponse.getPopulationCounts().
                get(populationResponse.getPopulationCounts().size() - 1);

        return CountryResponse.builder()
                .capital(countryApi.getCapitalData(country).getData().getCapital())
                .currency(currencyResponse.getCurrency())
                .iso2(currencyResponse.getIso2())
                .iso3(currencyResponse.getIso3())
                .population(populationCount.getValue())
                .location(countryApi.getLocationData(country).getData())
                .build();
    }

}
