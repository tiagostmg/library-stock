package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceSearchViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.UpdateBookInstanceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-instances")
public class BookInstanceController {

    @Autowired
    private BookInstanceService bookInstanceService;

    @PostMapping
    public ResponseEntity<BookInstanceViewModel> create(@RequestBody AddBookInstanceViewModel bookInstance) {
        return ResponseEntity.ok(bookInstanceService.createBookInstance(bookInstance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookInstanceViewModel> update(@PathVariable int id, @RequestBody UpdateBookInstanceViewModel bookInstance) {
        return ResponseEntity.ok(bookInstanceService.updateBookInstance(id, bookInstance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bookInstanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{bookId}")
    public List<BookInstanceViewModel> findByBookId(@PathVariable int bookId) {
        return bookInstanceService.findByBookId(bookId);
    }

    @GetMapping
    public ResponseEntity<List<BookInstance>> findAll() {
        return ResponseEntity.ok(bookInstanceService.findAll());
    }

    @GetMapping("/{id}")
    public BookInstanceViewModel findByBookInstanceId(@PathVariable int id) {
        return bookInstanceService.findByBookInstanceId(id);
    }

    @GetMapping("/dash/{internalCode}")
    public BookInstanceSearchViewModel findByInternalCode(@PathVariable String internalCode) {
        return bookInstanceService.findByInternalCode(internalCode);
    }

    @GetMapping("/dash/bad-preservation")
    public List<BookInstanceViewModel> findBadInstances() {
        return bookInstanceService.findByPreservationStateBad();
    }

}
