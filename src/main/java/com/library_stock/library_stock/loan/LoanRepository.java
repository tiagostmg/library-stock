package com.library_stock.library_stock.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
//    List<Loan> findByReturnedAtIsNullAndExpectedReturnDateBefore(LocalDate date);
    List<Loan> findByActualReturnDateIsNullAndExpectedReturnDateBefore(LocalDate date);
}
