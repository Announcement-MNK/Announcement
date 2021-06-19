package com.example.turboaz.dtos;

import com.example.turboaz.models.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {
    private Long id;
    private String name;

    public ModelDto(Model model){
        this.id = model.getId();
        this.name = model.getName();
    }
}
