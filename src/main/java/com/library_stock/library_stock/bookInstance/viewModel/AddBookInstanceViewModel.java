package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.location.Location;

import java.time.LocalDateTime;

public class AddBookInstanceViewModel {

    public String internalCode;

    public LocalDateTime acquisitionDate;

    public PreservationState preservationState;

    public BookStatus status;

    public int bookId;

    public int locationId;
}
