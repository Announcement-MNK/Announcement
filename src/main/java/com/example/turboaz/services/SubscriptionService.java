package com.example.turboaz.services;

import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.dtos.SubscriptionDto;
import com.example.turboaz.dtos.SubscriptionListDto;
import com.example.turboaz.exceptions.MingreaterthanMaxException;
import com.example.turboaz.exceptions.SubscriptionMaxCountException;
import com.example.turboaz.exceptions.SubscriptionNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.utils.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Subscription service.CRUD subscriptions
 */
@Service
public interface SubscriptionService {
    /**
     * @param subscriptionDto
     * @param username
     * @return SubscriptionDto
     */
    SubscriptionDto createSubscription(String username, SubscriptionDto subscriptionDto) throws SubscriptionMaxCountException, MingreaterthanMaxException;

    /**
     * @param subscriptionDto
     * @param username
     * @param subscriptionId
     * @return SubscriptionDto
     */
    SubscriptionDto updateSubscription(String username, Long subscriptionId, SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException, UserNotFoundException, MingreaterthanMaxException;

    /**
     * @param id
     * @param username
     */
    void deleteSubscription(String username, Long id) throws SubscriptionNotFoundException, UserNotFoundException;

    /**
     * @param username
     * @return List<SubscriptionListDto>
     */
    List<SubscriptionListDto> getAllSubscriptions(String username) throws UserNotFoundException;

    /**
     * @param username
     * @param id
     * @return SubscriptionDto
     */

    SubscriptionDto getSubscription(String username, Long id) throws SubscriptionNotFoundException, UserNotFoundException;

    /**
     * @param username
     * @return List<SubscriptionListDto>
     */
    List<SubscriptionListDto> getUserSubscriptions(String username);
}

