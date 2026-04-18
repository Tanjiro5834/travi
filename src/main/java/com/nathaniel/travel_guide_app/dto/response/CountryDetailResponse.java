package com.nathaniel.travel_guide_app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDetailResponse {
    private Long id;
    private String name;
    private String slug;
    private String region;
    private String subRegion;
    private String code;
    private String capital;
    private String currency;
    private String language;
    private String timeZone;
    private String bestTimeToVisit;
    private String safetyLevel;
    private String flagUrl;
    private String overview;
}