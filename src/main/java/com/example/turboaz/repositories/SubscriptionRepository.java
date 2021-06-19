package com.example.turboaz.repositories;

import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Subscription;
import com.example.turboaz.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> getSubscriptionsByUserId(Long id);

    Subscription getSubscriptionById(Long id);


    Page<Subscription> findSubscriptionByUsername(String username, Pageable pageable);

}
