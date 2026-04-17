package com.nathaniel.travel_guide_app.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CountryResponse {
    private Long id;
    private String name;
    private String region;
    private String flagEmoji;
    private String status;
    private LocalDateTime updatedAt;
}