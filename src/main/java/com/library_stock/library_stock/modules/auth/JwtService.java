package com.library_stock.library_stock.modules.auth;

import com.library_stock.library_stock.modules.user.UserModel;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "qtGrMpdsluzhas54ooHBSAk+CXRgaINHm2VclTAbFLQ=";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserModel userModel) {
        return Jwts.builder()
                .setSubject(userModel.getCpf())
                .claim("id", userModel.getId())
                .claim("nome", userModel.getFullName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractCpf(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserModel user) {
        String cpf = extractCpf(token);
        return cpf.equals(user.getCpf());
    }

}
