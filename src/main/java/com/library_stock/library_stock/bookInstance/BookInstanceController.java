package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceViewModel;
import com.library_stock.library_stock.bookInstance.viewModel.BookInstanceSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookinstances")
public class BookInstanceController {

    @Autowired
    private BookInstanceService bookInstanceService;

    @GetMapping("/book/{bookId}")
    public List<BookInstanceViewModel> findByBookId(@PathVariable int bookId) {
        return bookInstanceService.findByBookId(bookId);
    }

    @GetMapping("/{id}")
    public BookInstanceViewModel findByBookInstanceId(@PathVariable int id) {
        return bookInstanceService.findByBookInstanceId(id);
    }

    @GetMapping("/dashboard/{internalCode}")
    public BookInstanceSearchViewModel findByInternalCode(@PathVariable String internalCode) {
        return bookInstanceService.findByInternalCode(internalCode);
    }
}
