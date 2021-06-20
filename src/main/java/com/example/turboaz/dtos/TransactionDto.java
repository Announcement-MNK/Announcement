package com.example.turboaz.dtos;

import java.time.LocalDateTime;

import com.example.turboaz.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionDto {
    private long id;
    private Long listingId;
    private double amount;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public TransactionDto(Long listingId, double amount, LocalDateTime createdAt, boolean isDeleted) {
        this.listingId = listingId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }
    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.listingId = transaction.getListing().getId();
        this.amount = transaction.getAmount();
        this.createdAt = transaction.getCreatedAt();
        this.isDeleted = transaction.isDeleted();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
