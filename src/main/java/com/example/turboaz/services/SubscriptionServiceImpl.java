package com.example.turboaz.services;

import com.example.turboaz.dtos.*;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.SubscriptionNotFoundException;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.helpers.PagingHelper;
import com.example.turboaz.models.*;
import com.example.turboaz.repositories.*;
import com.example.turboaz.utils.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionServiceImpl implements SubscriptionService {
    ModelRepository modelRepository;
    SubscriptionRepository subscriptionRepository;
    MakeRepository makeRepository;
    UserRepository userRepository;
    CityRepository cityRepository;

    public SubscriptionServiceImpl(ModelRepository modelRepository, CityRepository cityRepository, SubscriptionRepository subscriptionRepository, MakeRepository makeRepository, UserRepository userRepository) {
        this.modelRepository = modelRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.makeRepository = makeRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto) {
        User user = userRepository.findById(userId).get();
        Subscription subscription = convertToEntity(subscriptionDto, user);
        subscriptionRepository.save(subscription);
        return subscriptionDto;
    }

    @Override
    public SubscriptionDto updateSubscription(Long userId, Long subscriptionId, SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepository.findSubscriptionByUserIdAndId(userId, subscriptionId);
        User user = userRepository.findById(userId).get();
        if (subscription != null) throw new SubscriptionNotFoundException("This subscription is not found");
        Subscription newSubscription = convertToEntity(subscriptionDto, user);
        newSubscription.setId(subscriptionId);
        subscriptionRepository.save(newSubscription);
        return subscriptionDto;
    }

    @Override
    public void deleteSubscription(Long userId, Long id) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepository.findSubscriptionByUserIdAndId(userId, id);
        if (subscription == null) throw new SubscriptionNotFoundException("This subscription is not found");
        subscriptionRepository.delete(subscription);
    }

    @Override
    public Paging<SubscriptionListDto> getAllSubscriptions(Long userId, int index, int size, String sortBy) {
        Pageable paging = PagingHelper.preparePage(index, size, sortBy);
        Page<Subscription> subscriptions = subscriptionRepository.findAll(paging);
        return new Paging<SubscriptionListDto>().toBuilder()
                .pageCount((long) subscriptions.getTotalPages())
                .pageSize(subscriptions.getTotalElements())
                .items(DtoHelper.convertToSubscriptionListDto(PagingHelper.getResult(subscriptions)))
                .build();
    }

    @Override
    public SubscriptionDto getSubscription(Long userId, Long id) throws SubscriptionNotFoundException {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription == null) throw new SubscriptionNotFoundException("Can't find such listing");
        return new SubscriptionDto(subscription.get());
    }

    @Override
    public Paging<SubscriptionListDto> getUserSubscriptions(String username, int index, int size, String sortBy) {
        Pageable paging = PagingHelper.preparePage(index, size, sortBy);
        Page<Subscription> subscriptions = subscriptionRepository.findSubscriptionByUsername(username, paging);
        return new Paging<SubscriptionListDto>().toBuilder()
                .pageCount((long) subscriptions.getTotalPages())
                .pageSize(subscriptions.getTotalElements())
                .items(DtoHelper.convertToSubscriptionListDto(PagingHelper.getResult(subscriptions)))
                .build();
    }

    public Subscription convertToEntity(SubscriptionDto subscriptionDto, User user) {
        Make make = makeRepository.findById(subscriptionDto.getMakeId()).get();
        Model model = modelRepository.findById(subscriptionDto.getModelId()).get();
        City city = cityRepository.findById(subscriptionDto.getCityId()).get();
        Subscription subscription = DtoHelper.convertSubscriptionDtoToEntity(subscriptionDto, model, city, make, user);
        return subscription;
    }
}
