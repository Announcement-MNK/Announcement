package com.example.turboaz.repositories;

import com.example.turboaz.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
}
