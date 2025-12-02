package com.library_stock.library_stock.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE l.id NOT IN (SELECT bi.location.id FROM BookInstance bi)")
    List<Location> findAvailableLocations();
}
