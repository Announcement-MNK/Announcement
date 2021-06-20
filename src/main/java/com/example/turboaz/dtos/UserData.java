package com.example.turboaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String username;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime registrationTime;

}
