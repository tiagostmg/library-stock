package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.loan.types.LoanStatus;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.loan.viewModel.ReturnOverdueViewModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService extends BaseService<Loan, Integer, LoanRepository>{

    public LoanService(LoanRepository repository) {
        super(repository);
    }

    public List<ReturnOverdueViewModel> overduenLoan() {

        List<Loan> overdueLoans = repository
                .findOverdueLoans(LocalDate.now());

        return overdueLoans.stream()
                .map(this::mapToReturnOverdueViewModel)
                .toList();
    }

    private ReturnOverdueViewModel mapToReturnOverdueViewModel(Loan loan) {
        ReturnOverdueViewModel vm = new ReturnOverdueViewModel();

        vm.setLoanDate(loan.getLoanDate());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());

        // Converter Book -> BookViewModel
        vm.setBook(mapToBookViewModel(loan.getBookInstance().getBook()));

        // Converter BookInstance -> BookInstanceViewModel
        vm.setBookInstance(mapToBookInstanceViewModel(loan.getBookInstance()));

        return vm;
    }

    private BookViewModel mapToBookViewModel(Book book) {
        BookViewModel vm = new BookViewModel();

        vm.setId(book.getId());
        vm.setTitle(book.getTitle());
        vm.setAuthor(book.getAuthor());
        vm.setPublisher(book.getPublisher());
        vm.setIsbn(book.getIsbn());
        vm.setCategory(book.getCategory());
        vm.setNotes(book.getNotes());

        return vm;
    }

    private BookInstanceViewModel mapToBookInstanceViewModel(BookInstance instance) {
        BookInstanceViewModel vm = new BookInstanceViewModel();

        vm.setId(instance.getId());
        vm.setInternalCode(instance.getInternalCode());
        vm.setAcquisitionDate(instance.getAcquisitionDate());
        vm.setPreservationState(instance.getPreservationState());
        vm.setStatus(instance.getStatus());

        // book dentro do instance
        vm.setBook(mapToBookViewModel(instance.getBook()));

        // location dentro do instance
        vm.setLocation(instance.getLocation());

        return vm;
    }

}
