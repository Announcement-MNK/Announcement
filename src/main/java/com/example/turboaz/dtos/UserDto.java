package com.example.turboaz.dtos;

import com.example.turboaz.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String fullName;
    private String username;
    private String phone;

    public UserDto(User user){
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.phone = user.getPhone();
    }
}
