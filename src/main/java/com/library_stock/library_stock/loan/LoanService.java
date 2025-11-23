package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.loan.types.LoanStatus;
import com.library_stock.library_stock.bookInstance.BookInstanceRepository;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.loan.viewModel.BorrowBookResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.ReturnOverdueViewModel;
import org.springframework.stereotype.Service;
import com.library_stock.library_stock.reader.Reader;
import com.library_stock.library_stock.reader.ReaderRepository;
import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService extends BaseService<Loan, Integer, LoanRepository>{

    @Autowired
    BookInstanceRepository bookInstanceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReaderRepository readerRepository;

    public LoanService(LoanRepository repository) {
        super(repository);
    }

    public BorrowBookResponseViewModel borrowBook(int bookInstanceId, int userId, int readerId, String notes) {

        BookInstance instance = bookInstanceRepository.findById(bookInstanceId)
                .orElseThrow(() -> new IllegalArgumentException("Book instance not found"));

        if (instance.getStatus() == BookStatus.LOST) {
            throw new IllegalStateException("Book instance is lost.");
        }
        if (instance.getStatus() == BookStatus.CHECKED_OUT) {
            throw new IllegalStateException("Book instance is already borrowed.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Reader not found"));

        // Criar Loan
        Loan loan = new Loan();
        loan.setLoanDate(LocalDate.now());
        loan.setExpectedReturnDate(LocalDate.now().plusDays(7));
        loan.setStatus(LoanStatus.IN_PROGRESS);
        if (notes != null && !notes.isEmpty()) {
            loan.setNotes(notes);
        }
        loan.setUser(user);
        loan.setReader(reader);
        loan.setBookInstance(instance);

        instance.setStatus(BookStatus.CHECKED_OUT);
        bookInstanceRepository.save(instance);

        repository.save(loan);

        return new BorrowBookResponseViewModel(loan.getExpectedReturnDate());
    }

    public void returnBook(int bookInstanceId) {

        Loan loan = repository.findByBookInstanceIdAndActualReturnDateIsNull(bookInstanceId)
                .orElseThrow(() -> new IllegalStateException("This book is not currently borrowed."));

        loan.setActualReturnDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.RETURNED);

        BookInstance instance = loan.getBookInstance();
        instance.setStatus(BookStatus.AVAILABLE);
        bookInstanceRepository.save(instance);

        repository.save(loan);
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
