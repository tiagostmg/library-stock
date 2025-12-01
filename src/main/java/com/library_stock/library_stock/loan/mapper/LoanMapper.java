package com.library_stock.library_stock.loan.mapper;

import com.library_stock.library_stock.bookInstance.mapper.BookInstanceMapper;
import com.library_stock.library_stock.loan.Loan;
import com.library_stock.library_stock.loan.viewModel.HistoryBookInstanceResponseViewModel;
import com.library_stock.library_stock.loan.viewModel.LoanViewModel;
import com.library_stock.library_stock.loan.viewModel.OverdueResponseViewModel;
import com.library_stock.library_stock.reader.mapper.ReaderMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

  private final BookInstanceMapper bookInstanceMapper;
  private final ReaderMapper readerMapper;

  public LoanMapper(BookInstanceMapper bookInstanceMapper, ReaderMapper readerMapper) {
    this.bookInstanceMapper = bookInstanceMapper;
    this.readerMapper = readerMapper;
  }

  public LoanViewModel toViewModel(Loan loan) {
    if (loan == null) {
      return null;
    }
    LoanViewModel vm = new LoanViewModel();
    vm.setLoanId(loan.getId());
    vm.setLoanDate(loan.getLoanDate());
    vm.setExpectedReturnDate(loan.getExpectedReturnDate());
    vm.setActualReturnDate(loan.getActualReturnDate());
    vm.setBookInstanceViewModel(bookInstanceMapper.toViewModel(loan.getBookInstance()));
    return vm;
  }

  public HistoryBookInstanceResponseViewModel toHistoryViewModel(Loan loan) {
    if (loan == null) {
      return null;
    }
    HistoryBookInstanceResponseViewModel vm = new HistoryBookInstanceResponseViewModel();
    vm.setLoanId(loan.getId());
    vm.setLoanDate(loan.getLoanDate());
    vm.setExpectedReturnDate(loan.getExpectedReturnDate());
    vm.setActualReturnDate(loan.getActualReturnDate());
    vm.setBookInstanceViewModel(bookInstanceMapper.toViewModel(loan.getBookInstance()));
    vm.setReaderViewModel(readerMapper.toViewModel(loan.getReader()));
    return vm;
  }

  public OverdueResponseViewModel toOverdueViewModel(Loan loan) {
    if (loan == null) {
      return null;
    }
    OverdueResponseViewModel vm = new OverdueResponseViewModel();
    vm.setLoanId(loan.getId());
    vm.setLoanDate(loan.getLoanDate());
    vm.setExpectedReturnDate(loan.getExpectedReturnDate());
    vm.setOverdueReaderViewModel(readerMapper.toOverdueViewModel(loan.getReader()));
    vm.setOverdueBookInstanceViewModel(bookInstanceMapper.toOverdueViewModel(loan.getBookInstance()));
    return vm;
  }
}
