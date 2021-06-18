package com.example.turboaz.models;

import com.example.turboaz.enums.*;

import java.time.LocalDateTime;

public class Listing {
    private long id;
    private Model model;
    private int year;
    private City city;
    private double mileage;
    private boolean hasBarter;
    private int price;
    private double engine;
    private FuelType fuelType;
    private GearBox gearBox;
    private ListingType type;
    private BodyType bodyType;
    private Color color;
    private boolean isDisabled;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
