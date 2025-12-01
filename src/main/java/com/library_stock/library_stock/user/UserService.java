package com.library_stock.library_stock.user;

import com.library_stock.library_stock.base.BaseService;
import com.library_stock.library_stock.user.viewModel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, Integer, UserRepository> {

    @Autowired
    private UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
    }

    @Autowired
    private com.library_stock.library_stock.user.mapper.UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE
    public UserViewModel createViewModel(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userMapper.toViewModel(repository.save(user));
    }

    // READ - todos
    public List<UserViewModel> findAllViewModels() {
        return repository.findAll().stream()
                .map(userMapper::toViewModel)
                .toList();
    }

    // READ - por ID
    public UserViewModel findByIdViewModel(int id) {
        return repository.findById(id)
                .map(userMapper::toViewModel)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    public UserViewModel updateViewModel(int id, User userDetails) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setCpf(userDetails.getCpf());
        String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
        user.setPassword(hashedPassword);
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());

        return userMapper.toViewModel(repository.save(user));
    }

    // DELETE
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }
}
