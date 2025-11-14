package com.library_stock.library_stock.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // CREATE
    public UserModel create(UserModel librarian) {
        return repository.save(librarian);
    }

    // READ - todos
    public List<UserModel> findAll() {
        return repository.findAll();
    }

    // READ - por ID
    public UserModel findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
    }

    // UPDATE
    public UserModel update(int id, UserModel librarianDetails) {
        UserModel librarian = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));

        librarian.setCpf(librarianDetails.getCpf());
        librarian.setPasswordHash(librarianDetails.getPasswordHash());
        librarian.setFullName(librarianDetails.getFullName());
        librarian.setEmail(librarianDetails.getEmail());

        return repository.save(librarian);
    }

    // DELETE
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Librarian not found");
        }
        repository.deleteById(id);
    }
}
