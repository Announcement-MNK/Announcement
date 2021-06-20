package com.example.turboaz.repositories;

import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Transaction;
import com.example.turboaz.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    @Modifying
    @Query("UPDATE Transaction t set t.isDeleted =?1 where t.id = ?2 and t.user = ?3 AND t.listing =?4")
    void softDeleteTransaction(boolean isDeleted, long transactionId, User user, Listing listing);
}
