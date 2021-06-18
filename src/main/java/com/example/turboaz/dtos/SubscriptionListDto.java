package com.example.turboaz.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionListDto {
    private long subId;
    private String name;
    private MakeDto make;
    private ModelDto model;
    private CityDto city;
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
    private LocalDateTime creationDate;
    private String color;
    private List<CarSpecDto> specs;
}
