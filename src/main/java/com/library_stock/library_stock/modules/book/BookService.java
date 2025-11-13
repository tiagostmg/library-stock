package com.library_stock.library_stock.modules.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // CREATE
    public BookModel create(BookModel book) {
        return repository.save(book);
    }

    // READ - todos
    public List<BookModel> findAll() {
        return repository.findAll();
    }

    // READ - por ID
    public BookModel findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // UPDATE
    public BookModel update(int id, BookModel bookDetails) {
        BookModel book = repository.findById(id)
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

