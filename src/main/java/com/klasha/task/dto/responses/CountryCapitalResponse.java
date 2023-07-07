package com.klasha.task.dto.responses;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryCapitalResponse {
    private String name;
    private String capital;
    private String iso2;
    private String iso3;

    public CountryCapitalResponse(String capital) {
        this.capital = capital;
    }
}
