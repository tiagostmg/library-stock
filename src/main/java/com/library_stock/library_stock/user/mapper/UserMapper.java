package com.library_stock.library_stock.user.mapper;

import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.user.viewModel.UserViewModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserViewModel toViewModel(User user) {
    if (user == null) {
      return null;
    }
    UserViewModel vm = new UserViewModel();
    vm.setId(user.getId());
    vm.setCpf(user.getCpf());
    vm.setFullName(user.getFullName());
    vm.setEmail(user.getEmail());
    return vm;
  }
}
