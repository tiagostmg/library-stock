package com.library_stock.library_stock.modules.bookInstance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookInstanceRepository extends JpaRepository<BookInstanceModel, Integer> {
    Optional<BookInstanceModel> findByInternalCode(String internalCode);
}
