package com.example.turboaz.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private int id;
    private Long listingId;
    private double amount;
    private LocalDateTime createdAt;
}
