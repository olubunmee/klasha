package com.klasha.task.api;

import com.klasha.task.config.FeignConfig;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.TopCityResponse;
import com.klasha.task.utils.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
@FeignClient(value = "cityApi", url = Constants.BASE_URL, configuration = FeignConfig.class)
public interface CityApi {
    @RequestMapping(method = GET, value = "/population/cities/filter/q")
    ApiResponse<List<TopCityResponse>> getTopCities(@RequestParam("country") String country);

    @RequestMapping(method = GET, value = "/state/cities/q")
    ApiResponse<List<String>> getCitiesData(@RequestParam("country") String country, @RequestParam("state") String state);
}
