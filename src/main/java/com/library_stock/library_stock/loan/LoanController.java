package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.loan.LoanService;
import com.library_stock.library_stock.loan.viewModel.ReturnOverdueViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping("/dash/overduenloan")
    public ResponseEntity<List<ReturnOverdueViewModel>> overduenLoan() {
        var result = service.overduenLoan();
        return ResponseEntity.ok(result);
    }



}
