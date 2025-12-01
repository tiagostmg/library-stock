package com.library_stock.library_stock.reader;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByCpf(String cpf);

    Page<Reader> findByCpfContainingIgnoreCase(String cpf, Pageable pageable);

    Page<Reader> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Reader> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
