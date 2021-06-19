package com.example.turboaz.repositories;

import com.example.turboaz.models.Subscription;
import com.example.turboaz.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> getSubscriptionsByUserId(Long id);

    Subscription getSubscriptionById(Long id);
}
