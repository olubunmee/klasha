package com.klasha.task.services.city;

import com.klasha.task.dto.responses.TopCityResponse;

import java.util.List;

public interface CityService {
        List<TopCityResponse> getTopCities(int limit);
}
