package com.nathaniel.travel_guide_app.dto.request.trip;

import java.util.List;

public record ActivitySlot(
    String startTime,
    String endTime,
    List<String> preferredCategories
) {}