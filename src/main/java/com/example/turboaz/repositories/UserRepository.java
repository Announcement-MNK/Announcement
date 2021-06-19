package com.example.turboaz.repositories;

import com.example.turboaz.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
