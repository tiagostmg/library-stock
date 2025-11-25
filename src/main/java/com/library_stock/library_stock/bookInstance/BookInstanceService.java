package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;
import com.library_stock.library_stock.location.LocationRepository;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceSearchViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.UpdateBookInstanceViewModel;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceService extends BaseService<BookInstance, Integer, BookInstanceRepository> {

    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;
    public BookInstanceService(BookInstanceRepository repository, BookRepository bookRepository, LocationRepository locationRepository) {
        super(repository);
        this.bookRepository = bookRepository;
        this.locationRepository = locationRepository;
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
                .orElseThrow(() -> new RuntimeException("Instancia do livro não encontrado"));
    }

    public BookInstanceViewModel createBookInstance(AddBookInstanceViewModel bookInstanceVM) {
        BookInstance bookInstance = new BookInstance();
        bookInstance.setInternalCode(bookInstanceVM.internalCode);
        bookInstance.setAcquisitionDate(bookInstanceVM.acquisitionDate);
        bookInstance.setStatus(bookInstanceVM.status);
        bookInstance.setPreservationState(bookInstanceVM.preservationState);
        Location location = locationRepository.findById(bookInstanceVM.locationId)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        bookInstance.setLocation(location);

        Book book = bookRepository.findById(bookInstanceVM.bookId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        bookInstance.setBook(book);


        BookInstance saved = repository.save(bookInstance);

        return mapToBookInstanceViewModel(saved);
    }

    public BookInstanceViewModel updateBookInstance(int id, UpdateBookInstanceViewModel bookInstanceVM) {

        BookInstance bookInstance = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookInstance not found with id: " + id));

        bookInstance.setPreservationState(bookInstanceVM.preservationState);
        bookInstance.setStatus(bookInstanceVM.status);
        Location location = locationRepository.findById(bookInstanceVM.locationId)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        bookInstance.setLocation(location);

        BookInstance updated = repository.save(bookInstance);

        return mapToBookInstanceViewModel(updated);
    }

    public List<BookInstanceViewModel> findByPreservationStateBad() {
        List<BookInstance> badInstances = repository
                .findByPreservationState(PreservationState.BAD);

        return badInstances.stream()
                .map(this::mapToBookInstanceViewModel)
                .toList();
    }

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

    private LocationViewModel mapToLocationViewModel(Location location) {
        LocationViewModel vm = new LocationViewModel();

        vm.setId(location.getId());
        vm.setSector(location.getSector());
        vm.setAisle(location.getAisle());
        vm.setShelf(location.getShelf());
        vm.setShelfLevel(location.getShelfLevel());
        vm.setPosition(location.getPosition());
        vm.setClassificationCode(location.getClassificationCode());

        return vm;
    }

}
