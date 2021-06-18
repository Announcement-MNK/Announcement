package com.example.turboaz.enums;

public enum TransactionPurpose {
    INCREASE("Increase balance amount"),
    VIP("Payment for give VIP status to listing"),
    ACTIVATION("Payment for activate disabled listing"),
    ADDITIONAL("Payment for adding second listing per month");

    private String text;

    TransactionPurpose(String text) {
        this.text = text;
    }
}
