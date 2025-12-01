package com.library_stock.library_stock.reader;

import com.library_stock.library_stock.reader.viewModel.ReaderSearchRequestViewModel;
import com.library_stock.library_stock.reader.viewModel.ReaderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("/{id}")
    public ResponseEntity<ReaderViewModel> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findByIdViewModel(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ReaderViewModel> findById(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/search")
    public Page<ReaderViewModel> searchReaders(ReaderSearchRequestViewModel searchModel) {
        return service.searchReaders(searchModel);
    }
}
