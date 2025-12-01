package com.library_stock.library_stock.book;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.mapper.BookMapper;
import com.library_stock.library_stock.book.viewModel.AddBookViewModel;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.book.viewModel.UpdateBookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book, Integer, BookRepository> {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;

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
            case "category" -> repository.findByCategoryContainingIgnoreCase(filter, pageable);
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

        return bookMapper.toViewModel(saved);
    }

    public BookViewModel updateBook(int id, UpdateBookViewModel vm) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setNotes(vm.notes);

        Book updated = repository.save(book);

        return bookMapper.toViewModel(updated);
    }

}
