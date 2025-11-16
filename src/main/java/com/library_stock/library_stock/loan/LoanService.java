package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.base.BaseService;


public class LoanService extends BaseService<Loan, Integer, LoanRepository>{

    public LoanService(LoanRepository repository) {
        super(repository);
    }



}
