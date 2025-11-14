package com.library_stock.library_stock.modules.auth.dto;

public record LoginResponse(String token, UserResponse user) {}