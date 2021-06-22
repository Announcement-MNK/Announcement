package com.example.turboaz.services;

import com.example.turboaz.dtos.TransactionDto;
import com.example.turboaz.enums.TransactionPurpose;
import com.example.turboaz.enums.TransactionType;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;

import java.util.List;

public interface TransactionService {
    /**
     * Create new transaction
     * @param userId
     * @param listingId
     * @param transactionDto
     * @return
     * @throws UserNotFoundException
     * @throws ListingNotFoundException
     */
    TransactionDto createTransaction(long userId, long listingId, TransactionDto transactionDto) throws UserNotFoundException, ListingNotFoundException;

    /**
     * Search for all user's transactions
     * @param userId
     * @param listingId
     * @return
     * @throws UserNotFoundException
     * @throws ListingNotFoundException
     */
    List<TransactionDto> getAllTransactions(long userId, long listingId) throws UserNotFoundException, ListingNotFoundException;

    /**
     * soft deleting transaction, just making it unavailable
     * @param userId
     * @param listingId
     * @param transactionId
     * @throws UserNotFoundException
     * @throws ListingNotFoundException
     */
    void softDelete(long userId, long listingId, long transactionId) throws UserNotFoundException, ListingNotFoundException;
}
