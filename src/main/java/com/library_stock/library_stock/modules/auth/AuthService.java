package com.library_stock.library_stock.modules.auth;

import com.library_stock.library_stock.modules.auth.dto.LoginResponse;
import com.library_stock.library_stock.modules.auth.dto.UserResponse;
import com.library_stock.library_stock.modules.user.UserModel;
import com.library_stock.library_stock.modules.user.UserRepository;
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

    public LoginResponse login(String cpf, String senha) {

        UserModel user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF inválido."));

        if (!passwordEncoder.matches(senha, user.getPassword())) {
            throw new RuntimeException("Senha incorreta!");
        }


        String token = jwtService.generateToken(user);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getCpf()
        );

        return new LoginResponse(token, userResponse);
    }}
