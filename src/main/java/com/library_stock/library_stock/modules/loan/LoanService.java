package com.library_stock.library_stock.modules.loan;

import com.library_stock.library_stock.modules.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public class LoanService extends BaseService<LoanModel, Integer, LoanRepository>{

    public LoanService(LoanRepository repository) {
        super(repository);
    }



}
