package com.library_stock.library_stock.modules.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {
    Optional<BookModel> findByIsbn(String number);
}
