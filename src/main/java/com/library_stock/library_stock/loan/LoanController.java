package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.loan.viewModel.BorrowBookRequestViewModel;
import com.library_stock.library_stock.loan.viewModel.BorrowBookResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.ReturnBookRequestViewModel;
import com.library_stock.library_stock.loan.viewModel.ReturnOverdueViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/borrow")
    public ResponseEntity<BorrowBookResponseViewModel> borrowBook(BorrowBookRequestViewModel request) {
        int bookInstanceId = request.bookInstanceId();
        int userId = request.userId();
        int readerId = request.readerId();
        String notes =  request.notes();
        return ResponseEntity.ok(service.borrowBook(bookInstanceId, userId, readerId, notes));
    }

    @PostMapping("/return")
    public void returnBook(ReturnBookRequestViewModel request) {
        int id = request.id();
        service.returnBook(id);
    }


}
