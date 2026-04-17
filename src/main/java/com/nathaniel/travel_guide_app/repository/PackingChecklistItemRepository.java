package com.nathaniel.travel_guide_app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nathaniel.travel_guide_app.entity.PackingChecklistItem;

public interface PackingChecklistItemRepository extends JpaRepository<PackingChecklistItem, Long> {
    List<PackingChecklistItem> findByCountryIdOrderByCategoryAscItemNameAsc(Long countryId);
}
