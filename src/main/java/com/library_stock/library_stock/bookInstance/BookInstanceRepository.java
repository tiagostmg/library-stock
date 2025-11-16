package com.library_stock.library_stock.bookInstance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Integer> {
    Optional<BookInstance> findByInternalCode(String internalCode);
}
