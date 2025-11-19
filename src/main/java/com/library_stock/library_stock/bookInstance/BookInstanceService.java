package com.library_stock.library_stock.bookInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInstanceService {

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    public List<BookInstance> findByBookId(int bookId) {
        return bookInstanceRepository.findByBookId(bookId);
    }
}
