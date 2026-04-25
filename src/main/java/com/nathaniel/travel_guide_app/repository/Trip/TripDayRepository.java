package com.nathaniel.travel_guide_app.repository.Trip;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nathaniel.travel_guide_app.entity.TripDay;

public interface TripDayRepository extends JpaRepository<TripDay, Long> {
    List<TripDay> findByTripIdOrderByDayNumberAsc(Long tripId);
    @Query("""
    SELECT DISTINCT td
    FROM TripDay td
    LEFT JOIN FETCH td.activities a
    WHERE td.trip.id = :tripId
    ORDER BY td.dayNumber
    """)
    List<TripDay> findByTripIdWithActivities(Long tripId);
}