package com.library_stock.library_stock.modules.location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationModel, Integer> {
    Optional<LocationModel> findByClassificationCode(String classificationCode);
}
