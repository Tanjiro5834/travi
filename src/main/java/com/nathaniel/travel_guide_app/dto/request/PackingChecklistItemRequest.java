package com.nathaniel.travel_guide_app.dto.request;

import com.nathaniel.travel_guide_app.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackingChecklistItemRequest {
    private Long countryId;  
    private Country country;
    private String category; 
    private String itemName;
    private Boolean essential;
    private String note;
}