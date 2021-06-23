package com.example.turboaz.dtos;

import com.example.turboaz.models.Make;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeDto {
    private long id;
    @NotNull(message = "Field cannot be null")
    private String name;

    public MakeDto(Make make){
        this.id = make.getId();
        this.name = make.getName();
    }
}
