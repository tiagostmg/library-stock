package com.library_stock.library_stock.book.mapper;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

  public BookViewModel toViewModel(Book book) {
    if (book == null) {
      return null;
    }
    return new BookViewModel(
        book.getId(),
        book.getTitle(),
        book.getAuthor(),
        book.getPublisher(),
        book.getIsbn(),
        book.getCategory(),
        book.getNotes());
  }
}
