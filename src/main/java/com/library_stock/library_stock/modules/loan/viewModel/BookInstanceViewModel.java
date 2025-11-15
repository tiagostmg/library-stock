package com.library_stock.library_stock.modules.loan.viewModel;

import com.library_stock.library_stock.modules.book.BookModel;
import com.library_stock.library_stock.modules.bookInstance.types.BookStatus;
import com.library_stock.library_stock.modules.location.LocationModel;
import jakarta.persistence.*;
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

    public LocationModel location;
}
