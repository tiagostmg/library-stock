package com.library_stock.library_stock.book;

import com.library_stock.library_stock.book.types.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String number);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    // Busca categoria listada no enum Category.
    Page<Book> findByCategory(Category category, Pageable pageable);

    // Busca todos os livros de uma categoria específica.
    List<Book> findByCategory(Category category);

}