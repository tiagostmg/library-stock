package com.library_stock.library_stock.reader.mapper;

import com.library_stock.library_stock.reader.Reader;
import com.library_stock.library_stock.reader.viewModel.ReaderViewModel;
import com.library_stock.library_stock.reader.viewModel.OverdueReaderViewModel;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

  public ReaderViewModel toViewModel(Reader reader) {
    if (reader == null) {
      return null;
    }
    ReaderViewModel vm = new ReaderViewModel();
    vm.setId(reader.getId());
    vm.setName(reader.getName());
    vm.setEmail(reader.getEmail());
    vm.setPhone(reader.getPhone());
    vm.setCpf(reader.getCpf());
    vm.setAddress(reader.getAddress());
    return vm;
  }

  public OverdueReaderViewModel toOverdueViewModel(Reader reader) {
    if (reader == null) {
      return null;
    }
    OverdueReaderViewModel vm = new OverdueReaderViewModel();
    vm.setId(reader.getId());
    vm.setCpf(reader.getCpf());
    vm.setName(reader.getName());
    return vm;
  }
}
