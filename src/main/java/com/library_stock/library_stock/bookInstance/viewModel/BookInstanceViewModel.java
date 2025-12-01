package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInstanceViewModel {

    public int id;

    public String internalCode;

    public LocalDateTime acquisitionDate;

    public PreservationState preservationState;

    public BookStatus status;

    public BookViewModel book;

    public LocationViewModel location;
}
