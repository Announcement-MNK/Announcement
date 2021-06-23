package com.example.turboaz.services;

import com.example.turboaz.dtos.*;

public interface UserService {

    /**
     * Register new user via keycloak
     * @param user
     * @return
     */
    RegisterResponseDto register(RegisterPostDto user);

    /**
     * Login via keycloak
     * @param user
     * @return
     */
    LoginResponseDto login(LoginPostDto user);
}
