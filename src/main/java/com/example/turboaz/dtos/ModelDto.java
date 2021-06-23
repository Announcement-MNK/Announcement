package com.example.turboaz.dtos;

import com.example.turboaz.models.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {
    private Long id;
    @NotNull(message = "Field cannot be null")
    private String name;

    public ModelDto(Model model){
        this.id = model.getId();
        this.name = model.getName();
    }
}
