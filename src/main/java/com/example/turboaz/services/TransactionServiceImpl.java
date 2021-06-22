package com.example.turboaz.services;

import com.example.turboaz.dtos.TransactionDto;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Transaction;
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.ListingRepository;
import com.example.turboaz.repositories.TransactionRepository;
import com.example.turboaz.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    UserRepository userRepository;
    TransactionRepository transactionRepository;
    ListingRepository listingRepository;
    @Override
    public TransactionDto createTransaction(long userId, long listingId, TransactionDto dto) throws UserNotFoundException,ListingNotFoundException{
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");

        Listing listing = listingRepository.findById(listingId).get();
        if (listing == null) throw new ListingNotFoundException("This listing not found");

        Transaction transaction = DtoHelper.convertTransactionDtoToEntity(dto,user,listing);
        userRepository.updateUserBalance(userId,(user.getBalance() - transaction.getAmount()));
        transactionRepository.save(transaction);
        return dto;
    }

    @Override
    public List<TransactionDto> getAllTransactions(long userId, long listingId) throws UserNotFoundException, ListingNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");

        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for (Transaction transaction : user.getTransactionList()){
            transactionDtoList.add(new TransactionDto(transaction));
        }
        return transactionDtoList;
    }

    @Override
    public void softDelete(long userId, long listingId, long transactionId) throws UserNotFoundException, ListingNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");
        Listing listing = listingRepository.findById(listingId).get();
        if (listing == null) throw new ListingNotFoundException("This listing not found");
        transactionRepository.softDeleteTransaction(true,transactionId,user,listing);
        userRepository.updateUserBalance(userId,(user.getBalance()+transactionRepository.findById(transactionId).get().getAmount()));
    }
}
