package com.example.turboaz.dtos;

import com.example.turboaz.models.CarSpecification;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CarSpecDto {
    @NotNull(message = "Field cannot be null")
    @Min(value = 3,message = "Minimum message length is 3")
    private String name;

    public CarSpecDto(CarSpecification carSpecification){
        this.name = carSpecification.getName();
    }
}
