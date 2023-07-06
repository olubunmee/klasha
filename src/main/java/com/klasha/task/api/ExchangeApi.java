package com.klasha.task.api;

import com.klasha.task.config.FeignConfig;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.klasha.task.utils.Constants.BASE_URL;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
@FeignClient(value = "exchangeApi", url = BASE_URL, configuration = FeignConfig.class)
public interface ExchangeApi {

    @RequestMapping(method = GET, value = "/currency/q")
    ApiResponse<CurrencyResponse> getCurrencyData(@RequestParam("country") String country);
}
