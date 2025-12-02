package com.library_stock.library_stock.bookInstance.mapper;

import com.library_stock.library_stock.book.mapper.BookMapper;
import com.library_stock.library_stock.bookInstance.BookInstance;
import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.OverdueBookInstanceViewModel;
import org.springframework.stereotype.Component;

@Component
public class BookInstanceMapper {

  private final BookMapper bookMapper;

  public BookInstanceMapper(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  public BookInstanceViewModel toViewModel(BookInstance bookInstance) {
    if (bookInstance == null) {
      return null;
    }
    BookInstanceViewModel vm = new BookInstanceViewModel();
    vm.setId(bookInstance.getId());
    vm.setInternalCode(bookInstance.getInternalCode());
    vm.setAcquisitionDate(bookInstance.getAcquisitionDate());
    vm.setPreservationState(bookInstance.getPreservationState());
    vm.setStatus(bookInstance.getStatus());
    vm.setBook(bookMapper.toViewModel(bookInstance.getBook()));
    vm.setLocation(bookInstance.getLocation());
    return vm;
  }

  public BookInstance toModel(AddBookInstanceViewModel bookInstanceViewModel) {
    BookInstance bookInstance = new BookInstance();
    bookInstance.setStatus(bookInstanceViewModel.status);
    bookInstance.setPreservationState(bookInstanceViewModel.preservationState);

    return bookInstance;
  }

  public OverdueBookInstanceViewModel toOverdueViewModel(BookInstance bookInstance) {
    if (bookInstance == null) {
      return null;
    }
    OverdueBookInstanceViewModel vm = new OverdueBookInstanceViewModel();
    vm.setId(bookInstance.getId());
    vm.setInternalCode(bookInstance.getInternalCode());
    vm.setBook(bookMapper.toViewModel(bookInstance.getBook()));
    return vm;
  }
}
