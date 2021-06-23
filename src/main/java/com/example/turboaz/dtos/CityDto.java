package com.example.turboaz.dtos;

import com.example.turboaz.models.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Long id;
    @NotNull(message = "Field cannot be null")
    @Min(value = 3,message = "City name length cannot be null")
    private String name;

    public CityDto(City city){
        this.id = city.getId();
        this.name = city.getName();
    }
}
