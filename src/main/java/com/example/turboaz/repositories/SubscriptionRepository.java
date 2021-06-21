package com.example.turboaz.repositories;

import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Subscription;
import com.example.turboaz.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> getSubscriptionsByUserId(Long id);

    Subscription getSubscriptionById(Long id);

    @Query(value = "SELECT s FROM Subscription s WHERE l.user.username = :username", nativeQuery = true)
    Page<Subscription> findSubscriptionByUsername(String username, Pageable pageable);

}
