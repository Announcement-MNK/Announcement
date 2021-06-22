package com.example.turboaz.dtos;

import java.time.LocalDateTime;

import com.example.turboaz.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private long id;
    private Long listingId;
    private double amount;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public TransactionResponseDto(Long listingId, double amount, LocalDateTime createdAt, boolean isDeleted) {
        this.listingId = listingId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }
    public TransactionResponseDto(Transaction transaction) {
        this.id = transaction.getId();
        this.listingId = transaction.getListing().getId();
        this.amount = transaction.getAmount();
        this.createdAt = transaction.getCreatedAt();
        this.isDeleted = transaction.isDeleted();
    }

}
