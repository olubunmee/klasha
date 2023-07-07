package com.klasha.task.services.state;

import com.klasha.task.api.CityApi;
import com.klasha.task.api.StateApi;
import com.klasha.task.dto.responses.ApiResponse;
import com.klasha.task.dto.responses.CountryStateResponse;
import com.klasha.task.dto.responses.StateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StateServiceImplTest {

    @Mock
    private StateApi stateApi;

    @Mock
    private CityApi cityApi;

    private StateServiceImpl stateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stateService = new StateServiceImpl(stateApi, cityApi);
    }

    @Test
    public void testGetStateDetails() {

        String country = "TestCountry";
        CountryStateResponse countryStateResponse = new CountryStateResponse();
        StateResponse stateResponse1 = new StateResponse("State1");
        StateResponse stateResponse2 = new StateResponse("State2");
        countryStateResponse.setStates(Arrays.asList(stateResponse1, stateResponse2));

        when(stateApi.getStateData(country)).thenReturn(ApiResponse.ok(countryStateResponse));
        when(cityApi.getCitiesData(country, "State1")).thenReturn(ApiResponse.ok(Arrays.asList("City1", "City2")));
        when(cityApi.getCitiesData(country, "State2")).thenReturn(ApiResponse.ok(Arrays.asList("City3", "City4")));

        List<StateResponse> result = stateService.getStateDetails(country);


        verify(stateApi, times(1)).getStateData(country);
        verify(cityApi, times(1)).getCitiesData(country, "State1");
        verify(cityApi, times(1)).getCitiesData(country, "State2");

        assertEquals(2, result.size());

        StateResponse resultState1 = result.get(0);
        assertEquals("State1", resultState1.getName());
        assertEquals(Arrays.asList("City1", "City2"), resultState1.getCities());

        StateResponse resultState2 = result.get(1);
        assertEquals("State2", resultState2.getName());
        assertEquals(Arrays.asList("City3", "City4"), resultState2.getCities());
    }
}
