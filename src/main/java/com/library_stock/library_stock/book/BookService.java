package com.library_stock.library_stock.book;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.types.Category;
import com.library_stock.library_stock.book.viewModel.AddBookViewModel;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.book.viewModel.UpdateBookViewModel;
import com.library_stock.library_stock.location.Location;
import com.library_stock.library_stock.location.LocationRepository;
import com.library_stock.library_stock.location.viewModel.LocationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public class BookService extends BaseService<Book, Integer, BookRepository> {

    @Autowired
    private BookRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    private final Map<Category, Integer> defaultLocationByCategory = Map.ofEntries(
            Map.entry(Category.FICTION, 1),
            Map.entry(Category.NON_FICTION, 2),
            Map.entry(Category.HISTORY, 3),
            Map.entry(Category.SCIENCE, 4),
            Map.entry(Category.TECHNOLOGY, 5),
            Map.entry(Category.BIOGRAPHY, 6),
            Map.entry(Category.FANTASY, 7),
            Map.entry(Category.MYSTERY, 8),
            Map.entry(Category.ROMANCE, 9),
            Map.entry(Category.PHILOSOPHY, 10),
            Map.entry(Category.EDUCATION, 11),
            Map.entry(Category.ART, 12),
            Map.entry(Category.RELIGION, 13)
    );

    protected BookService(BookRepository repository) {
        super(repository);
    }

    public Page<Book> searchBooks(BookSearchViewModel bookSearch) {

        String filter = bookSearch.getFilter();
        String type = bookSearch.getType();

        // 1. Cria o objeto Pageable a partir dos dados do View Model
        // O PageRequest.of(página, tamanho) é a implementação concreta de Pageable.

        Pageable pageable = PageRequest.of(
                bookSearch.getPage(), bookSearch.getSize());

        // 2. Verifica o filtro
        if (filter == null || filter.isBlank()) {
            // Se não houver filtro, você pode retornar uma página vazia ou todos os livros
            // paginados
            // Vamos retornar todos os livros paginados neste caso:
            return repository.findAll(pageable);
        }

        filter = filter.toLowerCase().trim();

        // 3. Executa a busca com base no tipo e no objeto Pageable
        return switch (type.toLowerCase()) {
            case "title" -> repository.findByTitleContainingIgnoreCase(filter, pageable);
            case "author" -> repository.findByAuthorContainingIgnoreCase(filter, pageable);
            case "category" -> {
                try {
                    Category cat = Category.valueOf(filter.toUpperCase());
                    yield repository.findByCategory(cat, pageable);
                } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Categoria inválida: " + filter);
                }
            }
            default -> throw new IllegalArgumentException("Tipo de filtro inválido: " + type);
        };
    }

    public BookViewModel createBook(AddBookViewModel bookVM) {

        Book book = new Book();
        book.setTitle(bookVM.title);
        book.setAuthor(bookVM.author);
        book.setPublisher(bookVM.publisher);
        book.setIsbn(bookVM.isbn);
        book.setCategory(bookVM.category);
        book.setNotes(bookVM.notes);

        Book saved = repository.save(book);

        // localização padrão baseada na categoria
        LocationViewModel recommendedLocation = getDefaultLocation(book.getCategory());

        return mapToBookViewModel(saved, recommendedLocation);
    }

    private LocationViewModel getDefaultLocation(Category category) {

        Integer locationId = defaultLocationByCategory.get(category);

        if (locationId == null)
            return null;

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Localização padrão não encontrada"));

        LocationViewModel vm = new LocationViewModel();
        vm.setId(location.getId());
        vm.setSector(location.getSector());
        vm.setAisle(location.getAisle());
        vm.setShelf(location.getShelf());
        vm.setShelfLevel(location.getShelfLevel());
        vm.setClassificationCode(location.getClassificationCode());

        return vm;
    }

    private BookViewModel mapToBookViewModel(Book book, LocationViewModel recommendedLocation) {
        BookViewModel vm = mapToBookViewModel(book);
        vm.setRecommendedLocation(recommendedLocation);
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

    public BookViewModel updateBook(int id, UpdateBookViewModel vm) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        book.setNotes(vm.notes);

        Book updated = repository.save(book);

        // retorna a localização padrão
        LocationViewModel recommendedLocation = getDefaultLocation(book.getCategory());

        return mapToBookViewModel(updated, recommendedLocation);
    }

    public List<BookViewModel> findByCategory(Category category) {
        List<Book> books = repository.findByCategory(category);

        return books.stream()
                .map(this::mapToBookViewModel)
                .toList();
    }

}
