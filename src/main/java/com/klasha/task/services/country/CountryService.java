package com.klasha.task.services.country;

import com.klasha.task.dto.responses.CountryResponse;

public interface CountryService {

    CountryResponse getCountryDetail(String country);

}