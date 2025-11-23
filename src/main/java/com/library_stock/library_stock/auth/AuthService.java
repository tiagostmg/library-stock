package com.library_stock.library_stock.auth;

import com.library_stock.library_stock.auth.viewModel.LoginResponseViewModel;
import com.library_stock.library_stock.user.User;
import com.library_stock.library_stock.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseViewModel login(String cpf, String senha) {

        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF inválido."));

        if (!passwordEncoder.matches(senha, user.getPassword())) {
            throw new RuntimeException("Senha incorreta!");
        }


        String token = jwtService.generateToken(user);

        return new LoginResponseViewModel(token);
    }}
