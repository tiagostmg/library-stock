package com.library_stock.library_stock.modules.auth;

import com.library_stock.library_stock.modules.auth.dto.LoginRequest;
import com.library_stock.library_stock.modules.auth.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req.cpf(), req.password()));
    }
}