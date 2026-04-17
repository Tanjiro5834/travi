package com.nathaniel.travel_guide_app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nathaniel.travel_guide_app.entity.LocalTip;

public interface LocalTipRepository extends JpaRepository<LocalTip, Long> {     
    List<LocalTip> findByCountryIdOrderByCategoryAsc(Long countryId); 
}