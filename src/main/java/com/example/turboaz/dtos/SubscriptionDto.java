package com.example.turboaz.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private long subId;
    private String name;
    private long makeId;
    private long modelId;
    private long cityId;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
    private int fuelType;
    private int bodyType;
    private boolean loanOption;
    private boolean leaseOption;
    private boolean cashOption;
    private boolean barterOption;
    private String color;
    private List<Long> specs;
}
