package com.library_stock.library_stock.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Query("SELECT DISTINCT l FROM Loan l WHERE l.expectedReturnDate < :date AND l.status <> 'RETURNED'")
    List<Loan> findOverdueLoans(LocalDate date);

    Optional<Loan> findByBookInstanceIdAndActualReturnDateIsNull(int bookInstanceId);
}
