package com.nathaniel.travel_guide_app.dto.DTOs;

import java.math.BigDecimal;

public record BudgetSummaryDTO(
    BigDecimal totalBudget,
    BigDecimal totalSpent,
    BigDecimal remaining,
    BigDecimal averageDailySpent,
    String statusMessage // e.g., "Under Budget", "Critically High"
) {}