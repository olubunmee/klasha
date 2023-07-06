package com.klasha.task.api;

import com.klasha.task.config.FeignConfig;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CountryCapitalResponse;
import com.klasha.task.dto.responses.LocationResponse;
import com.klasha.task.dto.responses.PopulationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
@FeignClient(value = "countryApi", url = "https://countriesnow.space/api/v0.1/countries", configuration = FeignConfig.class)
public interface CountryApi {


    @RequestMapping(method = GET, value = "/population/q")
    ApiResponse<PopulationResponse> getPopulationData(@RequestParam("country") String country);



    @RequestMapping(method = GET, value = "/positions/q")
    ApiResponse<LocationResponse> getLocationData(@RequestParam("country") String country);



    @RequestMapping(method = GET, value = "/capital/q")
    ApiResponse<CountryCapitalResponse> getCapitalData(@RequestParam("country") String country);


}