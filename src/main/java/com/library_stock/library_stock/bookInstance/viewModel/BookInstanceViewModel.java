package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.location.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookInstanceViewModel {

    public int id;

    public String internalCode;

    public LocalDateTime acquisitionDate;

    public String preservationState;

    public BookStatus status;

    public BookViewModel book;

    public Location location;
}
