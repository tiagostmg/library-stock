package com.library_stock.library_stock.user;

import com.library_stock.library_stock.user.viewModel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserViewModel> create(@RequestBody User user) {
        return ResponseEntity.ok(service.createViewModel(user));
    }

    @GetMapping
    public ResponseEntity<List<UserViewModel>> findAll() {
        return ResponseEntity.ok(service.findAllViewModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewModel> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findByIdViewModel(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserViewModel> update(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(service.updateViewModel(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
