package com.klasha.task.dto.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.klasha.task.dto.PopulationCount;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PopulationResponse {

    private String country;

    private List<PopulationCount> populationCounts;

    private String code;

    private String iso3;
}