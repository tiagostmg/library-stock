package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.loan.Loan;
import com.library_stock.library_stock.reader.viewModel.ReaderViewModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryBookInstanceResponseViewModel {

    private int loanId;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private BookInstanceViewModel BookInstanceViewModel;

    private ReaderViewModel ReaderViewModel;

    public HistoryBookInstanceResponseViewModel toViewModel(Loan loan) {
        HistoryBookInstanceResponseViewModel vm = new HistoryBookInstanceResponseViewModel();

        vm.setLoanId(loan.getId());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());
        vm.setLoanDate(loan.getLoanDate());

        BookInstanceViewModel bookInstanceVM = new BookInstanceViewModel();
        bookInstanceVM.setId(loan.getBookInstance().getId());
        bookInstanceVM.setInternalCode(loan.getBookInstance().getInternalCode());
        bookInstanceVM.setAcquisitionDate(loan.getBookInstance().getAcquisitionDate());
        BookViewModel bookVM = new BookViewModel().toViewModel(loan.getBookInstance().getBook());
        bookInstanceVM.setBook(bookVM);

        vm.setBookInstanceViewModel(bookInstanceVM);

        ReaderViewModel readerVM = new ReaderViewModel();
        readerVM.setAddress(loan.getReader().getAddress());
        readerVM.setName(loan.getReader().getName());
        readerVM.setEmail(loan.getReader().getEmail());
        readerVM.setPhone(loan.getReader().getPhone());
        readerVM.setCpf(loan.getReader().getCpf());
        readerVM.setId(loan.getReader().getId());
        vm.setReaderViewModel(readerVM);


        return vm;
    }
}
