package com.klasha.task.controller;

import com.klasha.task.dto.responses.TopCityResponse;
import com.klasha.task.services.city.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityControllerTest {

    @Mock
    private CityService cityService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CityController cityController = new CityController(cityService);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    public void testGetCities() throws Exception {
        int numberOfCities = 5;
        List<TopCityResponse> topCities = Arrays.asList(new TopCityResponse("City1"), new TopCityResponse("City2"));

        when(cityService.getTopCities(numberOfCities)).thenReturn(topCities);

        mockMvc.perform(get("/api/v1/cities")
                .param("numberOfCities", String.valueOf(numberOfCities)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].city").value("City1"))
                .andExpect(jsonPath("$.data[1].city").value("City2"));
        verify(cityService, times(1)).getTopCities(numberOfCities);
    }
}
