package com.example.turboaz.services;

import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.dtos.SubscriptionDto;
import com.example.turboaz.dtos.SubscriptionListDto;
import com.example.turboaz.exceptions.SubscriptionNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.utils.Paging;

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
    SubscriptionDto updateSubscription(Long userId, Long subscriptionId, SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException, UserNotFoundException;

    /**
     * @param id
     * @param userId
     */
    void deleteSubscription(Long userId, Long id) throws SubscriptionNotFoundException, UserNotFoundException;

    /**
     * @param userId
     * @return List<SubscriptionListDto>
     */
    List<SubscriptionListDto> getAllSubscriptions(Long userId) throws UserNotFoundException;

    /**
     * @param userId
     * @param id
     * @return SubscriptionDto
     */

    SubscriptionDto getSubscription(Long userId, Long id) throws SubscriptionNotFoundException, UserNotFoundException;

    /**
     * @param username
     * @return List<SubscriptionListDto>
     */
    List<SubscriptionListDto> getUserSubscriptions(String username);
}

