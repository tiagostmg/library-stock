package com.library_stock.library_stock.book;

import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok(service.create(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> findByTitleContainingIgnoreCase(@RequestBody BookSearchViewModel bookSearchViewModel) {
        return ResponseEntity.ok(service.searchBooks(bookSearchViewModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody Book book) {
        return ResponseEntity.ok(service.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
