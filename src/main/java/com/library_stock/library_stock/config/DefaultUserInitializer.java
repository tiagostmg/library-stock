package com.library_stock.library_stock.config;

import com.library_stock.library_stock.modules.user.UserModel;
import com.library_stock.library_stock.modules.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultUserInitializer {

    // Certifique-se de que este hash BCrypt corresponde à senha "12345678"
    // Use o hash que você gerou!
    private static final String BRUNO_HASH = "$2a$10$IjmV7qEyMJya6fSNqnrkG.GcHzM4xDOzhobVBceyrWum8QI9Euoq6";

    @Bean
    public CommandLineRunner initializeDefaultUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Verifica se o usuário padrão já existe (usando o CPF)
            if (userRepository.findByCpf("01234567890").isEmpty()) {

                // Cria o usuário
                UserModel bruno = new UserModel();
                bruno.setCpf("01234567890");
                bruno.setFullName("Bruno Lopes");
                // Usamos o hash pré-gerado, não a senha em texto puro
                bruno.setPassword(BRUNO_HASH);

                userRepository.save(bruno);
                System.out.println(">>> USUÁRIO PADRÃO 'BRUNO LOPES' CRIADO NO BANCO DE DADOS.");
            }
        };
    }
}