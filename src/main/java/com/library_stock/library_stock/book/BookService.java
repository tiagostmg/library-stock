package com.library_stock.library_stock.book;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService extends BaseService<Book, Integer, BookRepository> {

    @Autowired
    private BookRepository repository;

    protected BookService(BookRepository repository) {
        super(repository);
    }

    public List<Book> searchBooks(BookSearchViewModel bookSearchViewModel) {

        String filter = bookSearchViewModel.getFilter();
        String type = bookSearchViewModel.getType();

        if (filter == null || filter.isBlank()) {
            return List.of(); // retorna vazio se não houver filtro
        }

        filter = filter.toLowerCase().trim();

        return switch (type.toLowerCase()) {
            case "title" -> repository.findByTitleContainingIgnoreCase(filter);
            case "author" -> repository.findByAuthorContainingIgnoreCase(filter);
            case "category" -> repository.findByCategoryContainingIgnoreCase(filter);
            default -> throw new IllegalArgumentException("Tipo de filtro inválido: " + type);
        };
    }


}

