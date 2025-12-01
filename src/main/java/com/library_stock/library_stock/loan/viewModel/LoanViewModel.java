package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LoanViewModel {
    private int loanId;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private LocalDateTime actualReturnDate;

    private BookInstanceViewModel BookInstanceViewModel;

}
