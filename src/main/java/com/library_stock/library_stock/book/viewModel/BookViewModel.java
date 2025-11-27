package com.library_stock.library_stock.book.viewModel;

import com.library_stock.library_stock.book.Book;
import lombok.Data;

@Data
public class BookViewModel {

    public int id;

    public String title;

    public String author;

    public String publisher;

    public String isbn;

    public String category;

    public String notes;

    public BookViewModel toViewModel(Book book) {
        BookViewModel vm = new BookViewModel();
        
        vm.setId(book.getId());
        vm.setTitle(book.getTitle());
        vm.setAuthor(book.getAuthor());
        vm.setPublisher(book.getPublisher());
        vm.setIsbn(book.getIsbn());
        vm.setCategory(book.getCategory());
        vm.setNotes(book.getNotes());
        
        return vm;
    }
}
