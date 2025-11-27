package com.library_stock.library_stock.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByClassificationCode(String classificationCode);

    @Query("SELECT l FROM Location l WHERE l.id NOT IN (SELECT bi.location.id FROM BookInstance bi)")
    List<Location> findAvailableLocations();
}
