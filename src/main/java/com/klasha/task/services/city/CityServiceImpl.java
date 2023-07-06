package com.klasha.task.services.city;

import com.klasha.task.api.CityApi;
import com.klasha.task.dto.responses.TopCityResponse;
import com.klasha.task.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityApi cityApi;

    @Override
    public List<TopCityResponse> getTopCities(int limit) {
        return Constants.COUNTRIES.stream()
                .flatMap(country -> cityApi.getTopCities(country).getData().parallelStream())
                .sorted(Comparator.comparingDouble(topCityResponse -> -topCityResponse.getPopulationCounts().get(0).getValue()))
                .limit(limit).toList();

    }
}
