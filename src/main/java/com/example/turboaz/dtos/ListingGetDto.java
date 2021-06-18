package com.example.turboaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingGetDto {
    private Long id;
    private MakeDto make;
    private ModelDto model;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private CityDto city;
    private String gearBox;
    private boolean autoPay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    private String type;
    private String thumbnailUrl;
    private List<CarSpecDto> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;
}
