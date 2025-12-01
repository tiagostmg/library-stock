package com.library_stock.library_stock.book.viewModel;

import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.types.Category;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;
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

    public Category category;

    public String notes;

    private LocationViewModel recommendedLocation;

    public static BookViewModel toViewModel(Book book, LocationViewModel location) {
        return new BookViewModel(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getCategory(),
                book.getNotes(),
                location
        );
    }
}
