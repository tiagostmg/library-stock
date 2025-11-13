package com.library_stock.library_stock.modules.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookModel> create(@RequestBody BookModel book) {
        return ResponseEntity.ok(service.create(book));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> update(@PathVariable int id, @RequestBody BookModel book) {
        return ResponseEntity.ok(service.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
