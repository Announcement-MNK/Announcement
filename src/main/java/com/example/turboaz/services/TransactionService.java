package com.example.turboaz.services;

import com.example.turboaz.dtos.TransactionDto;
import com.example.turboaz.enums.TransactionPurpose;
import com.example.turboaz.enums.TransactionType;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(long userId, long listingId, TransactionDto transactionDto) throws UserNotFoundException, ListingNotFoundException;
    List<TransactionDto> getAllTransactions(long userId, long listingId) throws UserNotFoundException, ListingNotFoundException;
    void softDelete(long userId, long listingId, long transactionId) throws UserNotFoundException, ListingNotFoundException;
}
