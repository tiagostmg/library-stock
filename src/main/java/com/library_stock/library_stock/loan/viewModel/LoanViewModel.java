package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.loan.Loan;
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

    public LoanViewModel toViewModel(Loan loan) {
        LoanViewModel vm = new LoanViewModel();

        vm.setLoanId(loan.getId());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());
        vm.setLoanDate(loan.getLoanDate());
        vm.setActualReturnDate(loan.getActualReturnDate());

        BookInstanceViewModel bookInstanceVM = new BookInstanceViewModel();

        bookInstanceVM.setId(loan.getBookInstance().getId());
        bookInstanceVM.setInternalCode(loan.getBookInstance().getInternalCode());
        BookViewModel bookVM = new BookViewModel().toViewModel(loan.getBookInstance().getBook());
        bookInstanceVM.setBook(bookVM);

        vm.setBookInstanceViewModel(bookInstanceVM);

        return vm;
    }
}
