package com.klasha.task.api;

import com.klasha.task.config.FeignConfig;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CountryStateResponse;
import com.klasha.task.utils.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Component
@FeignClient(value = "stateApi", url = Constants.BASE_URL, configuration = FeignConfig.class)
public interface StateApi {
     @RequestMapping(method = GET, value = "/states/q")
     ApiResponse<CountryStateResponse> getStateData(@RequestParam("country") String country);
}
