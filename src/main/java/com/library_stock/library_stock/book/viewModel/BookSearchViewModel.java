package com.library_stock.library_stock.book.viewModel;

import lombok.Data;

@Data
public class BookSearchViewModel {
    private String filter;
    private String type;

    // Usar 'int' garante um valor padrão (0) se não for fornecido na URL
    private int page = 0;

    // Usar 'int' garante um valor padrão (10) se não for fornecido na URL
    private int size = 10;
}