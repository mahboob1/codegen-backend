package com.mhc.iamservice.service;

import com.mhc.iamservice.dto.LoginRequest;
import com.mhc.iamservice.dto.RegisterRequest;
import com.mhc.iamservice.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

