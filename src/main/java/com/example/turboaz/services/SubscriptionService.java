package com.example.turboaz.services;

import com.example.turboaz.dtos.SubscriptionDto;
import com.example.turboaz.dtos.SubscriptionListDto;

import java.util.List;

/**
 * Subscription service.CRUD subscriptions
 */
public interface SubscriptionService {
    /**
     * @param subscriptionDto
     * @param userId
     * @return SubscriptionDto
     */
    SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto);

    /**
     * @param subscriptionDto
     * @param userId
     * @param subscriptionId
     * @return SubscriptionDto
     */
    SubscriptionDto updateSubscription(Long userId, Long subscriptionId, SubscriptionDto subscriptionDto);

    /**
     * @param id
     * @param userId
     */
    void deleteSubscription(Long userId, Long id);

    /**
     * @param userId
     * @return List<SubscriptionListDto>
     */
    List<SubscriptionListDto> getAllSubscriptions(Long userId);

    /**
     * @param userId
     * @param id
     * @return SubscriptionDto
     */

    SubscriptionDto getSubscription(Long userId, Long id);
}
