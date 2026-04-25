package com.nathaniel.travel_guide_app.service.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.nathaniel.travel_guide_app.dto.DTOs.BudgetSummaryDTO;
import com.nathaniel.travel_guide_app.entity.Trip;
import com.nathaniel.travel_guide_app.entity.TripActivity;
import com.nathaniel.travel_guide_app.entity.TripDay;
import com.nathaniel.travel_guide_app.repository.Trip.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetTrackingService {

    private final TripRepository tripRepository;

    // Start with zero.

    // Loop through every day of the trip.

    // For each day, loop through every activity.

    // Get the activity's estimated cost.

    // If it's not null (not all activities have costs), add it to the running total.

    // Return the sum.
    public BudgetSummaryDTO getBudgetSummary(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));

        BigDecimal totalBudget = trip.getTotalBudget();
        if (totalBudget == null) {
            totalBudget = BigDecimal.ZERO;
        }
        
        BigDecimal totalSpent = calculateTotalSpent(trip);
        BigDecimal remaining = totalBudget.subtract(totalSpent);
        
        int numberOfDays = trip.getNumberOfDays();
        BigDecimal averageDailySpent = BigDecimal.ZERO;
        
        if (numberOfDays > 0) {
            averageDailySpent = totalSpent.divide(
                BigDecimal.valueOf(numberOfDays), 
                2, 
                RoundingMode.HALF_UP
            );
        }

        String status = determineStatus(remaining, totalBudget);

        return new BudgetSummaryDTO(
            totalBudget,
            totalSpent,
            remaining,
            averageDailySpent,
            status
        );
    }

    private BigDecimal calculateTotalSpent(Trip trip) {
        BigDecimal total = BigDecimal.ZERO;

        for (TripDay day : trip.getTripDays()) {
            for (TripActivity activity : day.getActivities()) {
                BigDecimal cost = activity.getEstimatedCost();
                
                if (cost != null) {
                    total = total.add(cost);
                }
            }
        }

        return total;
    }

    private String determineStatus(BigDecimal remaining, BigDecimal total) {
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return "NO_BUDGET_SET";
        }

        // If remaining is negative, user has overspent
        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
            return "OVER_BUDGET";
        }

        // Calculate ratio: remaining / total
        BigDecimal ratio = remaining.divide(total, 2, RoundingMode.HALF_UP);

        // Less than 15% remaining = critical
        if (ratio.compareTo(new BigDecimal("0.15")) < 0) {
            return "CRITICAL";
        }

        return "HEALTHY";
    }
}