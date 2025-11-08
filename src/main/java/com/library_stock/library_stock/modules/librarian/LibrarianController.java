package com.library_stock.library_stock.modules.librarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService service;

    @PostMapping
    public ResponseEntity<LibrarianModel> create(@RequestBody LibrarianModel librarian) {
        return ResponseEntity.ok(service.create(librarian));
    }

    @GetMapping
    public ResponseEntity<List<LibrarianModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrarianModel> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibrarianModel> update(@PathVariable int id, @RequestBody LibrarianModel librarian) {
        return ResponseEntity.ok(service.update(id, librarian));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
