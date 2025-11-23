package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceSearchViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceService extends BaseService<BookInstance, Integer, BookInstanceRepository> {

    public BookInstanceService(BookInstanceRepository repository) {
        super(repository);
    }

    public List<BookInstanceViewModel> findByBookId(int bookId) {
        List<BookInstance> listByBookId = repository
                .findByBookId(bookId);

        return listByBookId.stream()
                .map(this::mapToBookInstanceViewModel)
                .toList();
    }

    public BookInstanceViewModel findByBookInstanceId(int id) {
        Optional<BookInstance> bookInstanceById = repository
                .findById(id);

        return bookInstanceById
                .map(this::mapToBookInstanceViewModel)
                .orElseThrow(() -> new RuntimeException("BookInstance não encontrado"));
    }

    public BookInstanceSearchViewModel findByInternalCode(String internalCode) {

        Optional<BookInstance> internalCodeBookInstance = repository
                .findByInternalCode(internalCode);

        return internalCodeBookInstance
                .map(this::mapToReturnBookInstanceDashBoardViewModel)
                .orElseThrow(() -> new RuntimeException("BookInstance não encontrado"));    }

    private BookInstanceSearchViewModel mapToReturnBookInstanceDashBoardViewModel(BookInstance bookInstance) {
        BookInstanceSearchViewModel vm = new BookInstanceSearchViewModel();
        vm.setId(bookInstance.getId());
        vm.setInternalCode(bookInstance.getInternalCode());
        vm.setLocation(bookInstance.getLocation());
        vm.setBook(mapToBookViewModel(bookInstance.getBook()));

        return vm;
    }

    private BookInstanceViewModel mapToBookInstanceViewModel(BookInstance bookInstance) {
        BookInstanceViewModel vm = new BookInstanceViewModel();
        vm.setId(bookInstance.getId());
        vm.setInternalCode(bookInstance.getInternalCode());
        vm.setAcquisitionDate(bookInstance.getAcquisitionDate());
        vm.setPreservationState(bookInstance.getPreservationState());
        vm.setStatus(bookInstance.getStatus());

        // book dentro do instance
        vm.setBook(mapToBookViewModel(bookInstance.getBook()));

        // location dentro do instance
        vm.setLocation(bookInstance.getLocation());

        return vm;
    }


    private BookViewModel mapToBookViewModel(Book book) {
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
