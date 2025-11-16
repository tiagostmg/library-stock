package com.library_stock.library_stock.reader;


import com.library_stock.library_stock.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends BaseService<Reader, Integer, ReaderRepository> {

    public ReaderService(ReaderRepository repository) {
        super(repository);
    }
}
