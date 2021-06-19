package com.example.turboaz.dtos;

import java.util.List;

import com.example.turboaz.models.Subscription;
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
    private String fuelType;
    private String bodyType;
    private boolean loanOption;
    private boolean leaseOption;
    private boolean cashOption;
    private boolean barterOption;
    private String color;
    private List<Long> specs;

    public SubscriptionDto(Subscription subscription) {
        this.name = subscription.getUser().getFullName();
        this.makeId = subscription.getMake().getId();
        this.modelId = subscription.getModel().getId();
        this.cityId = subscription.getCity().getId();
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
