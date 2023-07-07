package com.klasha.task.controller;

import com.klasha.task.dto.responses.StateResponse;
import com.klasha.task.services.state.StateService;
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

public class StateControllerTest {

    @Mock
    private StateService stateService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        StateController stateController = new StateController(stateService);
        mockMvc = MockMvcBuilders.standaloneSetup(stateController).build();
    }

    @Test
    public void testGetStateDetails() throws Exception {
        String country = "Nigeria";
        List<StateResponse> stateResponses = Arrays.asList(new StateResponse("State1"), new StateResponse("State2"));

        when(stateService.getStateDetails(country)).thenReturn(stateResponses);

        mockMvc.perform(get("/api/v1/states")
                        .param("country", country))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].name").value("State1"))
                .andExpect(jsonPath("$.data[1].name").value("State2"));

        verify(stateService, times(1)).getStateDetails(country);
    }
}
