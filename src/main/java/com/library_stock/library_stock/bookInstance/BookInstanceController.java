package com.library_stock.library_stock.bookInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book-instances")
public class BookInstanceController {

    @Autowired
    private BookInstanceService bookInstanceService;

    @GetMapping("/book/{bookId}")
    public List<BookInstance> findByBookId(@PathVariable int bookId) {
        return bookInstanceService.findByBookId(bookId);
    }
}
