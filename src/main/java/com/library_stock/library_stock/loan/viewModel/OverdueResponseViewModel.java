package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.OverdueBookInstanceViewModel;
import com.library_stock.library_stock.loan.Loan;
import com.library_stock.library_stock.reader.viewModel.OverdueReaderViewModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OverdueResponseViewModel {

    private int loanId;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private OverdueBookInstanceViewModel overdueBookInstanceViewModel;

    private OverdueReaderViewModel overdueReaderViewModel;

    public OverdueResponseViewModel toViewModel(Loan loan) {
        OverdueResponseViewModel vm = new OverdueResponseViewModel();

        vm.setLoanId(loan.getId());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());
        vm.setLoanDate(loan.getLoanDate());

        OverdueReaderViewModel readerVM = new OverdueReaderViewModel();

        readerVM.setId(loan.getReader().getId());
        readerVM.setCpf(loan.getReader().getCpf());
        readerVM.setName(loan.getReader().getName());

        vm.setOverdueReaderViewModel(readerVM);

        OverdueBookInstanceViewModel bookInstanceVM = new OverdueBookInstanceViewModel();

        bookInstanceVM.setId(loan.getBookInstance().getId());
        bookInstanceVM.setInternalCode(loan.getBookInstance().getInternalCode());
        new BookViewModel();
        BookViewModel bookVM = BookViewModel.toViewModel(loan.getBookInstance().getBook());
        bookInstanceVM.setBook(bookVM);

        vm.setOverdueBookInstanceViewModel(bookInstanceVM);

        return vm;
    }
}

