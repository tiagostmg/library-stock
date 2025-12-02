package com.library_stock.library_stock.book.viewModel;

import lombok.Data;

@Data
public class BookSearchViewModel {
    private String filter;
    private String type;
    private int page = 0;
    private int size = 10;
}