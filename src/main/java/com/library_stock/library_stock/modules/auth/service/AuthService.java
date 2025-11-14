package com.library_stock.library_stock.modules.auth.service;

import com.library_stock.library_stock.modules.auth.dto.LoginResponse;
import com.library_stock.library_stock.modules.auth.dto.UserResponse;
import com.library_stock.library_stock.modules.user.UserModel;
import com.library_stock.library_stock.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(String cpf, String senha) {

        UserModel user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF inválido."));

        if (!user.getPasswordHash().equals(senha)) {
            throw new RuntimeException("Senha incorreta!");
        }

        String token = jwtService.generateToken(user);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getCpf()
        );

        return new LoginResponse(token, userResponse);
    }
}
