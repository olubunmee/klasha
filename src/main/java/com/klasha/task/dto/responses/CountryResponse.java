package com.klasha.task.dto.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CountryResponse {

    private String capital;

    private String iso2;

    private String iso3;

    private LocationResponse location;

    private String currency;

    private int population;
}