package com.klasha.task.services.city;

import com.klasha.task.api.CityApi;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.TopCityResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CityServiceImplTest {
    private final CityService cityService = mock(CityService.class);
    private final CityApi cityApi = mock(CityApi.class);

    @Test
    void getTopCities() {
        when(cityApi.getTopCities(any())).thenReturn(ApiResponse.ok(Collections.emptyList()));
        List<TopCityResponse> topCityResponses = cityService.getTopCities(10);
        assertNotNull(topCityResponses);
    }
}