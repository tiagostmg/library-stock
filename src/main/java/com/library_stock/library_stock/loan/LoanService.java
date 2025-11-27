package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.loan.types.LoanStatus;
import com.library_stock.library_stock.bookInstance.BookInstanceRepository;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.loan.viewModel.BorrowBookResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.OverdueResponseViewModel;
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


    public List<OverdueResponseViewModel> getOverdueLoans() {

        List<Loan> overdueLoans = repository
                .findOverdueLoans(LocalDate.now());

        return overdueLoans.stream()
                .map(e -> new OverdueResponseViewModel().toViewModel(e))
                .toList();
    }


}
