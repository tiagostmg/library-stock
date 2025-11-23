package com.library_stock.library_stock.loan.viewModel;

public record BorrowBookRequestViewModel (int bookInstanceId, int userId, int readerId, String notes) {
}
