package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnOverdueViewModel {

    public LocalDate loanDate;

    public LocalDate expectedReturnDate;

    public BookViewModel book;

    public BookInstanceViewModel bookInstance;

}
