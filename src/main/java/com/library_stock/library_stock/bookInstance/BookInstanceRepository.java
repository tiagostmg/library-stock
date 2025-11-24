package com.library_stock.library_stock.bookInstance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance, Integer> {
    Optional<BookInstance> findByInternalCode(String internalCode);

    List<BookInstance> findByBookId(int bookId);

    Optional<BookInstance> findById(int id);

}
