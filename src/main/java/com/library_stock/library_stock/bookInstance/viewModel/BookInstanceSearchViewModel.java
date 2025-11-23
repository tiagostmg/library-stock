package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.location.Location;
import lombok.Data;

@Data
public class BookInstanceSearchViewModel {
    public int id;

    public String internalCode;

    public BookViewModel book;

    public Location location;
}
