package com.example.turboaz.repositories;

import com.example.turboaz.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    @Modifying
    @Transactional
    @Query(value = "UPDATE User u set u.balance = :balance where u.id = :id")
    void updateUserBalance(long id,double balance);
}
