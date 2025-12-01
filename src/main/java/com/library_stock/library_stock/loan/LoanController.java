package com.library_stock.library_stock.loan;

import com.library_stock.library_stock.loan.viewModel.BorrowBookRequestViewModel;
import com.library_stock.library_stock.loan.viewModel.BorrowBookResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.OverdueResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping("/dash/overdue-loan")
    public ResponseEntity<List<OverdueResponseViewModel>> overduenLoan() {
        var result = service.getOverdueLoans();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowBookResponseViewModel> borrowBook(BorrowBookRequestViewModel request) {
        int bookInstanceId = request.bookInstanceId();
        String userCpf = request.userCpf();
        int readerId = request.readerId();
        String notes =  request.notes();
        return ResponseEntity.ok(service.borrowBook(bookInstanceId, userCpf, readerId, notes));
    }

    @PostMapping("/return/{id}")
    public void returnBook(@PathVariable int id) {
        service.returnBook(id);
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<LoanViewModel>> getByReaderId(@PathVariable int readerId)
    {
        var result = service.getByReaderId(readerId);
        return ResponseEntity.ok(result);
    }


    @GetMapping("book-instance/{bookInstanceId}")
    public ResponseEntity<List<HistoryBookInstanceResponseViewModel>> getByBookInstanceId(@PathVariable int bookInstanceId)
    {
        var result = service.getByBookInstanceId(bookInstanceId);
        return ResponseEntity.ok(result);
    }
}
