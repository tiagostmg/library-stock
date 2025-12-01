package com.library_stock.library_stock.reader;


import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.book.Book;
import com.library_stock.library_stock.book.viewModel.BookSearchViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReaderService extends BaseService<Reader, Integer, ReaderRepository> {

    public ReaderService(ReaderRepository repository) {
        super(repository);
    }

    public Page<Reader> searchReaders(ReaderSearchRequestViewModel readerSearch) {

        String filter = readerSearch.getFilter();
        String type = readerSearch.getType();

        // 1. Cria o objeto Pageable a partir dos dados do View Model
        // O PageRequest.of(página, tamanho) é a implementação concreta de Pageable.

        Pageable pageable = PageRequest.of(
                readerSearch.getPage(), readerSearch.getSize());

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
            case "cpf" -> repository.findByCpfContainingIgnoreCase(filter, pageable);
            case "name" -> repository.findByNameContainingIgnoreCase(filter, pageable);
            case "email" -> repository.findByEmailContainingIgnoreCase(filter, pageable);
            default -> throw new IllegalArgumentException("Tipo de filtro inválido: " + type);
        };
    }
}
