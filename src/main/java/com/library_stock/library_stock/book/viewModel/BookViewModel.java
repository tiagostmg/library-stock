package com.library_stock.library_stock.book.viewModel;

import com.library_stock.library_stock.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookViewModel {

    public int id;

    public String title;

    public String author;

    public String publisher;

    public String isbn;

    public String category;

    public String notes;

    public static BookViewModel toViewModel(Book book) {
        return new BookViewModel(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getCategory(),
                book.getNotes()
        );
    }
}
