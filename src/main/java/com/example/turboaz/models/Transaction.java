package com.example.turboaz.models;

import com.example.turboaz.enums.TransactionPurpose;
import com.example.turboaz.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private User user;
    private Listing listing; // nullable
    private double amount;
    private TransactionPurpose purpose;
    private TransactionType type;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
