package com.example.turboaz.dtos;

import com.example.turboaz.models.CarSpecification;

public class CarSpecDto {
    private String name;

    public CarSpecDto(CarSpecification carSpecification){
        this.name = carSpecification.getName();
    }
}
