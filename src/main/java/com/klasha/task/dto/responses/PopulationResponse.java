package com.klasha.task.dto.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.klasha.task.dto.PopulationCount;
import lombok.*;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PopulationResponse {

    private String country;

    private List<PopulationCount> populationCounts;

    private String code;

    private String iso3;
}