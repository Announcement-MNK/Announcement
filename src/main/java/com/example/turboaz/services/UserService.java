package com.example.turboaz.services;

import com.example.turboaz.dtos.*;

public interface UserService {
    /**
     * Login via keycloak
     * @param user
     * @return
     */
    LoginResponseDto login(LoginPostDto user);

    /**
     * Register new user via keycloak
     * @param user
     * @return
     */
    RegisterResponseDto register(RegisterPostDto user);
}
