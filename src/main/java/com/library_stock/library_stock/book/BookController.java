package com.library_stock.library_stock.book;

import com.library_stock.library_stock.book.types.Category;
import com.library_stock.library_stock.book.viewModel.AddBookViewModel;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.book.viewModel.BookViewModel;
import com.library_stock.library_stock.book.viewModel.UpdateBookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookViewModel> create(@RequestBody AddBookViewModel book) {
        return ResponseEntity.ok(service.createBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public Page<Book> searchBooks(BookSearchViewModel searchModel) {
        // O Spring Framework automaticamente popula 'searchModel'
        // usando os query parameters (ex: ?filter=e&page=0&size=1)

        // Chamamos o serviço que já está configurado para usar o PageRequest.of()
        return service.searchBooks(searchModel);
    }

    @GetMapping("/search/by-category")
    public ResponseEntity<List<BookViewModel>> findByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(service.findByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookViewModel> update(@PathVariable int id, @RequestBody UpdateBookViewModel book) {
        return ResponseEntity.ok(service.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
