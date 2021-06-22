package com.example.turboaz.services;

import com.example.turboaz.dtos.RegisterPostDto;
import com.example.turboaz.dtos.RegisterResponseDto;
import com.example.turboaz.dtos.UserDto;

public interface UserService {
    /**
     * Login via keycloak
     * @param email
     * @param password
     * @return
     */
    UserDto login(String email, String password);

    /**
     * Register new user via keycloak
     * @param user
     * @param password
     * @return
     */
    RegisterResponseDto register(RegisterPostDto user, String password);
}
