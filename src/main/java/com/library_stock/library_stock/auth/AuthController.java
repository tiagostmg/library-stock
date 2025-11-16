package com.library_stock.library_stock.auth;

import com.library_stock.library_stock.auth.viewModel.LoginRequestViewModel;
import com.library_stock.library_stock.auth.viewModel.LoginResponseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseViewModel> login(@RequestBody LoginRequestViewModel req) {
        return ResponseEntity.ok(authService.login(req.cpf(), req.password()));
    }
}