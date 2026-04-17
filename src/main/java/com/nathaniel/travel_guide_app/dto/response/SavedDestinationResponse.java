package com.nathaniel.travel_guide_app.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SavedDestinationResponse {
    private Long id;
    private Long countryId;
    private String countryName;
    private String countrySlug;
    private String flagUrl;
    private LocalDateTime savedAt;
}
