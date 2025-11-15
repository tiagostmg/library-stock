package com.library_stock.library_stock.modules.loan;

import com.library_stock.library_stock.modules.base.BaseService;
import com.library_stock.library_stock.modules.book.BookModel;
import com.library_stock.library_stock.modules.bookInstance.BookInstanceModel;
import com.library_stock.library_stock.modules.loan.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.modules.loan.viewModel.BookViewModel;
import com.library_stock.library_stock.modules.loan.viewModel.ReturnOverduenViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class LoanService extends BaseService<LoanModel, Integer, LoanRepository>{

    public LoanService(LoanRepository repository) {
        super(repository);
    }

    public List<ReturnOverduenViewModel> overduenLoan() {

        List<LoanModel> overdueLoans = repository
                .findByReturnedAtIsNullAndExpectedReturnDateBefore(LocalDate.now());

        return overdueLoans.stream()
                .map(this::mapToReturnOverdueViewModel)
                .toList();
    }

    private ReturnOverduenViewModel mapToReturnOverdueViewModel(LoanModel loan) {
        ReturnOverduenViewModel vm = new ReturnOverduenViewModel();

        vm.setLoanDate(loan.getLoanDate());
        vm.setExpectedReturnDate(loan.getExpectedReturnDate());

        // Converter Book -> BookViewModel
        vm.setBook(mapToBookViewModel(loan.getBookInstance().getBook()));

        // Converter BookInstance -> BookInstanceViewModel
        vm.setBookInstance(mapToBookInstanceViewModel(loan.getBookInstance()));

        return vm;
    }

    private BookViewModel mapToBookViewModel(BookModel book) {
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

    private BookInstanceViewModel mapToBookInstanceViewModel(BookInstanceModel instance) {
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
