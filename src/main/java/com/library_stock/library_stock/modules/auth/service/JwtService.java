package com.library_stock.library_stock.modules.auth.service;

import com.library_stock.library_stock.modules.user.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    //TODO
    private static final String SECRET = "MINHA_CHAVE_SECRETA_64_CARACTERES"; // coloque no application.properties

    public String generateToken(UserModel userModel) {
        return Jwts.builder()
                .setSubject(userModel.getCpf())
                .claim("id", userModel.getId())
                .claim("nome", userModel.getFullName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
