package com.klasha.task.controller;

import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.StateResponse;
import com.klasha.task.services.state.StateService;
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
public class StateController {

    private final StateService stateService;


    @GetMapping("states")
    @Cacheable(value = "states", key = "#country")
    public ApiResponse<List<StateResponse>> getStateDetails(@RequestParam(defaultValue = "Nigeria") String country) {
        return ApiResponse.ok(stateService.getStateDetails(country));
    }
}
