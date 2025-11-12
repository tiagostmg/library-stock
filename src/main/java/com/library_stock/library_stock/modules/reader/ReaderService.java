package com.library_stock.library_stock.modules.reader;


import com.library_stock.library_stock.modules.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends BaseService<ReaderModel, Integer, ReaderRepository> {

    public ReaderService(ReaderRepository repository) {
        super(repository);
    }
}
