package com.klasha.task.controller;

import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.TopCityResponse;
import com.klasha.task.services.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CityController {

    private final CityService cityService;

    @GetMapping("cities")
     @Cacheable(value = "cities", key = "#numberOfCities")
    public ApiResponse<List<TopCityResponse>> getCities(@RequestParam(defaultValue = "5") int numberOfCities) {
        return ApiResponse.ok(cityService.getTopCities(numberOfCities));
    }
}
