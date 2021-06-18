package com.example.turboaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String username;
}
