package com.library_stock.library_stock.book.viewModel;

import com.library_stock.library_stock.book.types.Category;
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

}
