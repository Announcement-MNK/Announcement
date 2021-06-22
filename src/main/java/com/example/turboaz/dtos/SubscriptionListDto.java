package com.example.turboaz.dtos;

import com.example.turboaz.models.Subscription;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
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
    private String fuelType;
    private String bodyType;
    private boolean loanOption;
    private boolean leaseOption;
    private boolean cashOption;
    private boolean barterOption;
    private LocalDateTime creationDate;
    private String color;
    private List<CarSpecDto> specs;

    public SubscriptionListDto(Subscription subscription) {
        this.subId = subscription.getId();
        this.name = subscription.getUser().getFullName();
        this.make = new MakeDto(subscription.getMake());
        this.model = new ModelDto(subscription.getModel());
        this.city = new CityDto(subscription.getCity());
        this.minYear = subscription.getMinYear();
        this.maxYear = subscription.getMaxYear();
        this.minPrice = subscription.getMinPrice();
        this.maxPrice = subscription.getMaxPrice();
        this.minMileage = subscription.getMinMileage();
        this.maxMileage = subscription.getMaxMileage();
        this.fuelType = subscription.getFuelType().name();
        this.bodyType = subscription.getBodyType().name();
        this.loanOption = subscription.isHasLoan();
        this.leaseOption = subscription.isHasLease();
        this.cashOption = subscription.isHasCash();
        this.color = subscription.getColor().name();
    }
}
