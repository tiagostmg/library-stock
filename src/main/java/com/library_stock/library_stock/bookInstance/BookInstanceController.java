package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.bookInstance.viewModel.AddBookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
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
    public ResponseEntity<BookInstanceViewModel> create(@RequestBody AddBookInstanceViewModel vm) {
        return ResponseEntity.ok(bookInstanceService.createBookInstance(vm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookInstanceViewModel> update(@PathVariable int id, @RequestBody UpdateBookInstanceViewModel vm) {
        return ResponseEntity.ok(bookInstanceService.updateBookInstance(id, vm));
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
    public BookInstanceViewModel findByInternalCode(@PathVariable String internalCode) {
        return bookInstanceService.findByInternalCode(internalCode);
    }

    @GetMapping("/dash/bad-preservation")
    public List<BookInstanceViewModel> findBadInstances() {
        return bookInstanceService.findByPreservationStateBad();
    }
}
