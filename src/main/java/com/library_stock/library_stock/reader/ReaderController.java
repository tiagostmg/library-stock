package com.library_stock.library_stock.reader;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("/{id}")
    public Optional<Reader> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public Optional<Reader> findById(@PathVariable String cpf) {
        return service.findByCpf(cpf);
    }

    @GetMapping("/search")
    public Page<Reader> searchReaders(ReaderSearchRequestViewModel searchModel) {
        return service.searchReaders(searchModel);
    }
}
