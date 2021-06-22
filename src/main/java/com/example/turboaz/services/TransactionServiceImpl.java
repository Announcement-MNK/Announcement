package com.example.turboaz.services;

import com.example.turboaz.dtos.TransactionPostDto;
import com.example.turboaz.dtos.TransactionResponseDto;
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

    public TransactionServiceImpl(UserRepository userRepository,
            TransactionRepository transactionRepository,
            ListingRepository listingRepository){
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseDto createTransaction(long userId, TransactionPostDto dto) throws UserNotFoundException,ListingNotFoundException{
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");
        Listing listing = listingRepository.findById(dto.getListingId()).get();
        if (listing == null) throw new ListingNotFoundException("This listing not found");
        Transaction transaction = DtoHelper.convertTransactionDtoToEntity(dto,user,listing);
        transactionRepository.save(transaction);
        userRepository.updateUserBalance(userId,(user.getBalance() - transaction.getAmount()));
        return new TransactionResponseDto(transaction);
    }

    @Override
    public List<TransactionResponseDto> getAllTransactions(long userId, long listingId) throws UserNotFoundException, ListingNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");

        List<TransactionResponseDto> transactionDtoList = new ArrayList<>();
        for (Transaction transaction : user.getTransactionList()){
            transactionDtoList.add(new TransactionResponseDto(transaction));
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
