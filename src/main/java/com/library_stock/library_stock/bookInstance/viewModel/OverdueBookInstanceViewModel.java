package com.library_stock.library_stock.bookInstance.viewModel;

import com.library_stock.library_stock.book.viewModel.BookViewModel;
import lombok.Data;

@Data
public class OverdueBookInstanceViewModel {

    public int id;

    public String internalCode;

    public BookViewModel book;

}
