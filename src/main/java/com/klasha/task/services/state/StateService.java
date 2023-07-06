package com.klasha.task.services.state;

import com.klasha.task.dto.responses.StateResponse;

import java.util.List;

public interface StateService {
        List<StateResponse> getStateDetails(String country);
}
