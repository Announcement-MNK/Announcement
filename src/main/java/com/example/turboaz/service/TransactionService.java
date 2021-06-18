package com.example.turboaz.service;

import com.example.turboaz.dtos.TransactionDto;
import com.example.turboaz.enums.TransactionPurpose;
import com.example.turboaz.enums.TransactionType;
import com.example.turboaz.models.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(long listingId, TransactionPurpose purpose, TransactionType type, double amount);
    List<Transaction> getAllTransactions();
    void softDelete(long transactionId);
}
