package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.BookRepository;
import com.library_stock.library_stock.book.types.Category;
import com.library_stock.library_stock.bookInstance.mapper.BookInstanceMapper;
import com.library_stock.library_stock.bookInstance.types.BookStatus;
import com.library_stock.library_stock.location.LocationRepository;
import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.types.PreservationState;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.UpdateBookInstanceViewModel;
import com.library_stock.library_stock.location.Location;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookInstanceService extends BaseService<BookInstance, Integer, BookInstanceRepository> {

    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;
    private final BookInstanceMapper bookInstanceMapper;
    private final BookInstanceRepository bookInstanceRepository;

    public BookInstanceService(BookInstanceRepository repository, BookRepository bookRepository,
            LocationRepository locationRepository,
            BookInstanceMapper bookInstanceMapper, BookInstanceRepository bookInstanceRepository) {
        super(repository);
        this.bookRepository = bookRepository;
        this.locationRepository = locationRepository;
        this.bookInstanceMapper = bookInstanceMapper;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public List<BookInstanceViewModel> findByBookId(int bookId) {
        List<BookInstance> listByBookId = repository
                .findByBookId(bookId);

        return listByBookId.stream()
                .map(bookInstanceMapper::toViewModel)
                .toList();
    }

    public BookInstanceViewModel findByBookInstanceId(int id) {
        Optional<BookInstance> bookInstanceById = repository
                .findById(id);

        return bookInstanceById
                .map(bookInstanceMapper::toViewModel)
                .orElseThrow(() -> new RuntimeException("BookInstance não encontrado"));
    }

    public BookInstanceViewModel findByInternalCode(String internalCode) {

        Optional<BookInstance> internalCodeBookInstance = repository
                .findByInternalCode(internalCode);

        return internalCodeBookInstance
                .map(bookInstanceMapper::toViewModel)
                .orElseThrow(() -> new RuntimeException("Instancia do livro não encontrado"));
    }

    @Transactional
    public BookInstanceViewModel createBookInstance(int bookId, AddBookInstanceViewModel newInstanceDataViewModel) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        BookInstance newInstanceData = bookInstanceMapper.toModel(newInstanceDataViewModel);

        Location location = generateLocationForBook(book);

        locationRepository.save(location);

        newInstanceData.setBook(book);
        newInstanceData.setLocation(location);
        newInstanceData.setStatus(BookStatus.AVAILABLE);
        newInstanceData.setAcquisitionDate(LocalDateTime.now());
        newInstanceData.setInternalCode(java.util.UUID.randomUUID().toString());

        BookInstance newBook = bookInstanceRepository.save(newInstanceData);

        return bookInstanceMapper.toViewModel(newBook);
    }

    private Location generateLocationForBook(Book book) {
        Location location = new Location();

        defineAisleAndSector(book.getCategory(), location);

        int shelfNum = (book.getId() % 50) + 1;
        int levelNum = (book.getId() % 6) + 1;

        location.setShelf("Shelf-" + shelfNum);
        location.setShelfLevel("Level-" + levelNum);

        Integer maxPosition = bookInstanceRepository.findMaxPositionByBookId(book.getId());
        int nextPosition = (maxPosition == null) ? 1 : maxPosition + 1;

        location.setPosition(String.valueOf(nextPosition));

        return location;
    }

    private void defineAisleAndSector(Category category, Location location) {
        switch (category) {
            case Ficcao:
            case Fantasia:
            case Misterio:
            case Romance:
                location.setSector("Literature Wing");
                location.setAisle("Aisle-100");
                break;

            case Nao_ficcao:
            case Biografia:
            case Historia:
                location.setSector("Humanities Wing");
                location.setAisle("Aisle-200");
                break;

            case Ciencia:
            case Tecnologia:
            case Educacao:
                location.setSector("Science Wing");
                location.setAisle("Aisle-300");
                break;

            case Filosofia:
            case Religiao:
            case Arte:
                location.setSector("Arts Wing");
                location.setAisle("Aisle-400");
                break;

            default:
                location.setSector("General Storage");
                location.setAisle("Aisle-000");
        }
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

        return bookInstanceMapper.toViewModel(updated);
    }

    public List<BookInstanceViewModel> findByPreservationStateBad() {
        List<BookInstance> badInstances = repository
                .findByPreservationState(PreservationState.BAD);

        return badInstances.stream()
                .map(bookInstanceMapper::toViewModel)
                .toList();
    }

    public List<BookInstanceViewModel> findAllViewModels() {
        return repository.findAll().stream()
                .map(bookInstanceMapper::toViewModel)
                .toList();
    }

}
