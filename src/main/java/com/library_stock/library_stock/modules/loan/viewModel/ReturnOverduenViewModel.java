package com.library_stock.library_stock.modules.loan.viewModel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnOverduenViewModel {

    public LocalDate loanDate;

    public LocalDate expectedReturnDate;

    public BookViewModel book;

    public BookInstanceViewModel bookInstance;

}
