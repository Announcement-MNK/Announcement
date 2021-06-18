package com.example.turboaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingGetDTO {
    private Long id;
    private MakeDTO make;
    private ModelDTO model;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private CityDTO city;
    private String gearBox;
    private boolean auto_pay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    private String type;
    private String thumbnailUrl;
    private List<CarSpecDTO> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;
}
