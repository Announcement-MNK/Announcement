package com.example.turboaz.dtos;

import com.example.turboaz.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;

    public RegisterResponseDto(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
