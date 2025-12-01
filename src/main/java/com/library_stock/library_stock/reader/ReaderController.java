package com.library_stock.library_stock.reader;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/search")
    public Page<Reader> searchReaders(ReaderSearchRequestViewModel searchModel) {
        // O Spring Framework automaticamente popula 'searchModel'
        // usando os query parameters (ex: ?filter=e&page=0&size=1)

        // Chamamos o serviço que já está configurado para usar o PageRequest.of()
        return service.searchReaders(searchModel);
    }
}
