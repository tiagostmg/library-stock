package com.library_stock.library_stock.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserModel librarian) {
        return ResponseEntity.ok(service.create(librarian));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> update(@PathVariable int id, @RequestBody UserModel librarian) {
        return ResponseEntity.ok(service.update(id, librarian));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
