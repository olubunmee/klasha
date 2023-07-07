package com.klasha.task.services.country;

import com.klasha.task.api.CountryApi;
import com.klasha.task.api.ExchangeApi;
import com.klasha.task.dto.PopulationCount;
import com.klasha.task.dto.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CountryServiceImplTest {
    @Mock
    private CountryApi countryApi;

    @Mock
    private ExchangeApi exchangeApi;

    private CountryServiceImpl countryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        countryService = new CountryServiceImpl(countryApi, exchangeApi);
    }

    @Test
    public void testGetCountryDetail() {

        String country = "TestCountry";
        CurrencyResponse currencyResponse = new CurrencyResponse("Get","ISO2", "ISO3", "Currency");
        PopulationResponse populationResponse = new PopulationResponse();
        populationResponse.setPopulationCounts(new ArrayList<>());
        PopulationCount populationCount = new PopulationCount(12300303, 2023);
        populationResponse.getPopulationCounts().add(populationCount);


        when(exchangeApi.getCurrencyData(country)).thenReturn(ApiResponse.ok(currencyResponse));
        when(countryApi.getPopulationData(country)).thenReturn(ApiResponse.ok(populationResponse));
        when(countryApi.getCapitalData(country)).thenReturn(ApiResponse.ok(new CountryCapitalResponse("Capital")));
        when(countryApi.getLocationData(country)).thenReturn(ApiResponse.ok(new LocationResponse(123,456)));


        CountryResponse result = countryService.getCountryDetail(country);


        verify(exchangeApi, times(1)).getCurrencyData(country);
        verify(countryApi, times(1)).getPopulationData(country);
        verify(countryApi, times(1)).getCapitalData(country);
        verify(countryApi, times(1)).getLocationData(country);

        assertEquals("Capital", result.getCapital());
        assertEquals("Currency", result.getCurrency());
        assertEquals("ISO2", result.getIso2());
        assertEquals("ISO3", result.getIso3());
        assertEquals(12300303, result.getPopulation());

    }
}