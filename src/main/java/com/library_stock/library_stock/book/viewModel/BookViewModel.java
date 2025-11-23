package com.library_stock.library_stock.book.viewModel;

import lombok.Data;

@Data
public class BookViewModel {

    public int id;

    public String title;

    public String author;

    public String publisher;

    public String isbn;

    public String category;

    public String notes;
}
