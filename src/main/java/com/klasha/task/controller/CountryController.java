package com.klasha.task.controller;

import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CountryResponse;
import com.klasha.task.services.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryCityService;

    @GetMapping("country")
    public ApiResponse<CountryResponse> getCountryDetails(@RequestParam(defaultValue = "Nigeria") String country) {
        return ApiResponse.ok(countryCityService.getCountryDetail(country));
    }


}
