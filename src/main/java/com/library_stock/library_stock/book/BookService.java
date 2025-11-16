package com.library_stock.library_stock.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // CREATE
    public Book create(Book book) {
        return repository.save(book);
    }

    // READ - todos
    public List<Book> findAll() {
        return repository.findAll();
    }

    // READ - por ID
    public Book findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // UPDATE
    public Book update(int id, Book bookDetails) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublisher(bookDetails.getPublisher());
        book.setIsbn(bookDetails.getIsbn());
        book.setCategory(bookDetails.getCategory());
        book.setNotes(bookDetails.getNotes());

        return repository.save(book);
    }

    // DELETE
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        repository.deleteById(id);
    }
}

