package com.library_stock.library_stock.reader;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.library_stock.library_stock.reader.mapper.ReaderMapper;

@Service
public class ReaderService extends BaseService<Reader, Integer, ReaderRepository> {

    @Autowired
    private ReaderRepository repository;

    @Autowired
    private ReaderMapper readerMapper;

    public ReaderService(ReaderRepository repository) {
        super(repository);
    }

    public Page<ReaderViewModel> searchReaders(ReaderSearchRequestViewModel readerSearch) {
        String filter = readerSearch.getFilter();
        String type = readerSearch.getType();

        Pageable pageable = PageRequest.of(
                readerSearch.getPage(), readerSearch.getSize());

        Page<Reader> readers;

        if (filter == null || filter.isBlank()) {
            readers = repository.findAll(pageable);
        } else {
            filter = filter.toLowerCase().trim();
            readers = switch (type.toLowerCase()) {
                case "cpf" -> repository.findByCpfContainingIgnoreCase(filter, pageable);
                case "name" -> repository.findByNameContainingIgnoreCase(filter, pageable);
                case "email" -> repository.findByEmailContainingIgnoreCase(filter, pageable);
                default -> throw new IllegalArgumentException("Tipo de filtro inválido: " + type);
            };
        }

        return readers.map(readerMapper::toViewModel);
    }

    public ReaderViewModel findByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(readerMapper::toViewModel)
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado com CPF: " + cpf));
    }

    public ReaderViewModel findByIdViewModel(int id) {
        return repository.findById(id)
                .map(readerMapper::toViewModel)
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado com ID: " + id));
    }
}
