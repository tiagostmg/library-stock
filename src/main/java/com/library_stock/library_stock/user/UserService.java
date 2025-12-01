package com.library_stock.library_stock.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE
    public User create(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return repository.save(user);
    }

    // READ - todos
    public List<User> findAll() {
        return repository.findAll();
    }

    // READ - por ID
    public User findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    public User update(int id, User userDetails) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setCpf(userDetails.getCpf());
        String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
        user.setPassword(hashedPassword);
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());

        return repository.save(user);
    }

    // DELETE
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }
}
