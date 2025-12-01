package com.library_stock.library_stock.loan.viewModel;

public record BorrowBookRequestViewModel (int bookInstanceId, String userCpf, int readerId, String notes) {
}
