package com.example.turboaz.enums;

public enum ListingType {
    FREE(0),
    PAID(8),
    VIP(12);

    private final double amount;

    ListingType(double amount) {
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }
}
