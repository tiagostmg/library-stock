package com.library_stock.library_stock.modules.loan;

import com.library_stock.library_stock.modules.loan.viewModel.ReturnOverduenViewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanModel, Integer> {

    List<LoanModel> findByReturnedAtIsNullAndExpectedReturnDateBefore(LocalDate date);

}
