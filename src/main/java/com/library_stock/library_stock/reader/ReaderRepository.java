package com.library_stock.library_stock.reader;

import com.library_stock.library_stock.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Page<Reader> findByCpfContainingIgnoreCase(String cpf, Pageable pageable);

    Page<Reader> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Reader> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
