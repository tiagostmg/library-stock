package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.OverdueBookViewModel;
import com.library_stock.library_stock.loan.Loan;
import com.library_stock.library_stock.user.viewModel.OverdueUserViewModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OverdueResponseViewModel {

    private int id;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private OverdueResponseViewModel overdueLoanResponseViewModel;

    private OverdueUserViewModel overdueUserViewModel;

    public OverdueResponseViewModel toViewModel(Loan loan) {
        OverdueResponseViewModel vm = new OverdueResponseViewModel();

        vm.setId(loan.getId());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());
        vm.setLoanDate(loan.getLoanDate());

        OverdueUserViewModel userVM = new OverdueUserViewModel();

        userVM.setId(loan.getUser().getId());
        userVM.setCpf(loan.getUser().getCpf());
        userVM.setFullName(loan.getUser().getFullName());

        vm.setOverdueUserViewModel(userVM);

        OverdueBookViewModel bookInstanceVM = new OverdueBookViewModel();

        bookInstanceVM.setId(loan.getBookInstance().getId());
        bookInstanceVM.setInternalCode(loan.getBookInstance().getInternalCode());
        BookViewModel bookVM = new BookViewModel().toViewModel(loan.getBookInstance().getBook());
        bookInstanceVM.setBook(bookVM);

        return vm;
    }
}

