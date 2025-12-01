package com.library_stock.library_stock.loan.viewModel;

import com.library_stock.library_stock.bookInstance.viewModel.OverdueBookInstanceViewModel;
import com.library_stock.library_stock.reader.viewModel.OverdueReaderViewModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OverdueResponseViewModel {

    private int loanId;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private OverdueBookInstanceViewModel overdueBookInstanceViewModel;

    private OverdueReaderViewModel overdueReaderViewModel;

}
