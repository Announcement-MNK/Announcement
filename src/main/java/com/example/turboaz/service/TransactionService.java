package com.example.turboaz.service;

import com.example.turboaz.dtos.TransactionDto;
import com.example.turboaz.enums.TransactionPurpose;
import com.example.turboaz.enums.TransactionType;
import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(long userId, long listingId, TransactionPurpose purpose, TransactionType type, double amount);
    List<TransactionDto> getAllTransactions();
    void softDelete(long transactionId);
}
