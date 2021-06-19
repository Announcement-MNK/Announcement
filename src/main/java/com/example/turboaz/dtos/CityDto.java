package com.example.turboaz.dtos;

import com.example.turboaz.models.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Long id;
    private String name;

    public CityDto(City city){
        this.id = city.getId();
        this.name = city.getName();
    }
}
