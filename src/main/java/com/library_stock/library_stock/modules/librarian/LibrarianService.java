package com.library_stock.library_stock.modules.librarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository repository;

    // CREATE
    public LibrarianModel create(LibrarianModel librarian) {
        return repository.save(librarian);
    }

    // READ - todos
    public List<LibrarianModel> findAll() {
        return repository.findAll();
    }

    // READ - por ID
    public LibrarianModel findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));
    }

    // UPDATE
    public LibrarianModel update(int id, LibrarianModel librarianDetails) {
        LibrarianModel librarian = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));

        librarian.setUsername(librarianDetails.getUsername());
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
