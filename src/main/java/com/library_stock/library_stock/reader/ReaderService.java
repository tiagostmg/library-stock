package com.library_stock.library_stock.reader;


import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderService extends BaseService<Reader, Integer, ReaderRepository> {

    public ReaderService(ReaderRepository repository) {
        super(repository);
    }

    public Page<Reader> searchReaders(ReaderSearchRequestViewModel readerSearch) {

        String filter = readerSearch.getFilter();
        String type = readerSearch.getType();


        Pageable pageable = PageRequest.of(
                readerSearch.getPage(), readerSearch.getSize());

        if (filter == null || filter.isBlank()) {
            return repository.findAll(pageable);
        }

        filter = filter.toLowerCase().trim();

        return switch (type.toLowerCase()) {
            case "cpf" -> repository.findByCpfContainingIgnoreCase(filter, pageable);
            case "name" -> repository.findByNameContainingIgnoreCase(filter, pageable);
            case "email" -> repository.findByEmailContainingIgnoreCase(filter, pageable);
            default -> throw new IllegalArgumentException("Tipo de filtro inválido: " + type);
        };
    }

    public Optional<Reader> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
