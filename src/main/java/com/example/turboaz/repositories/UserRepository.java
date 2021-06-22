package com.example.turboaz.repositories;

import com.example.turboaz.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    @Modifying
    @Query("UPDATE User u set u.balance = ?2 where u.id = ?1")
    void updateUserBalance(long id,double balance);
}
