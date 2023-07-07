package com.klasha.task.controller;

import com.klasha.task.dto.responses.CountryResponse;
import com.klasha.task.services.country.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryControllerTest {

    @Mock
    private CountryService countryService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CountryController countryController = new CountryController(countryService);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    public void testGetCountryDetails() throws Exception {
        String country = "Nigeria";
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCapital("Abuja");

        when(countryService.getCountryDetail(country)).thenReturn(countryResponse);

        mockMvc.perform(get("/api/v1/country")
                .param("country", country))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.capital").value("Abuja"));

        verify(countryService, times(1)).getCountryDetail(country);
    }
}
