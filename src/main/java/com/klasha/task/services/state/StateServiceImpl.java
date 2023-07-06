package com.klasha.task.services.state;

import com.klasha.task.api.CityApi;
import com.klasha.task.api.StateApi;
import com.klasha.task.dto.responses.CountryStateResponse;
import com.klasha.task.dto.responses.StateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateApi stateApi;
    private final CityApi cityApi;

    @Override
    public List<StateResponse> getStateDetails(String country) {
        CountryStateResponse countryStateResponse = stateApi.getStateData(country).getData();
        return countryStateResponse.getStates().stream()
                .peek(stateResponse -> {
                    String stateName = stateResponse.getName().equalsIgnoreCase("Lagos State") ? "Lagos" : stateResponse.getName();
                    List<String> cities = cityApi.getCitiesData(country, stateName).getData();
                    stateResponse.setCites(cities);
                })
                .toList();
    }
}
