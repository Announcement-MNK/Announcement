package com.example.turboaz.models;

import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;

public class Subscription {
    private Make make;
    private Model model;
    private City city;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
    private FuelType fuelType;
    private BodyType bodyType;
    private boolean hasLoan;
    private boolean hasLease;
    private boolean hasCash;
}
