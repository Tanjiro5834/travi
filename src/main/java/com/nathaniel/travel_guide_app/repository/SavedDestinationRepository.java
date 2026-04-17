package com.nathaniel.travel_guide_app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nathaniel.travel_guide_app.entity.SavedDestination;

public interface SavedDestinationRepository extends JpaRepository<SavedDestination, Long> {
    List<SavedDestination> findByUserId(Long userId);
    Optional<SavedDestination> findByUserIdAndCountryId(Long userId, Long countryId);
    boolean existsByUserIdAndCountryId(Long userId, Long countryId);
    void deleteByUserIdAndCountryId(Long userId, Long countryId);
}