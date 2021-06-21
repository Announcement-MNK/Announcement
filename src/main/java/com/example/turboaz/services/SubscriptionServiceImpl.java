package com.example.turboaz.services;

import com.example.turboaz.dtos.*;
import com.example.turboaz.exceptions.*;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.helpers.PagingHelper;
import com.example.turboaz.models.*;
import com.example.turboaz.repositories.*;
import com.example.turboaz.utils.Paging;
import com.google.rpc.BadRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    public SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto) throws SubscriptionMaxCountException, MingreaterthanMaxException {
        User user = userRepository.findById(userId).get();
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getId()==userId).collect(Collectors.toList());
        if (subscriptions.size() < 5) {
            if(subscriptionDto.getMinMileage()<=subscriptionDto.getMaxMileage()
                    &&  subscriptionDto.getMinPrice()<=subscriptionDto.getMaxPrice()
                    &&subscriptionDto.getMinYear()<=subscriptionDto.getMinYear()){
                Subscription subscription = convertToEntity(subscriptionDto, user);
                subscriptionRepository.save(subscription);
                return subscriptionDto;
            }else{
                throw new MingreaterthanMaxException("Maximum value must be greater than minimum value");
            }


        } else {
            throw new SubscriptionMaxCountException("This user already has 5 subscriptions");
        }
    }

    @Override
    public SubscriptionDto updateSubscription(Long userId, Long subscriptionId, SubscriptionDto subscriptionDto) throws SubscriptionNotFoundException, UserNotFoundException, MingreaterthanMaxException {

        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("User is not found");
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getId()==userId).collect(Collectors.toList());
        Subscription subscription = subscriptions.stream()
                .filter(s -> s.getId() == subscriptionId).findAny().get();
        if (subscription == null) throw new SubscriptionNotFoundException("This subscription is not found");
        if(subscriptionDto.getMinMileage()<=subscriptionDto.getMaxMileage()
                &&  subscriptionDto.getMinPrice()<=subscriptionDto.getMaxPrice()
                &&subscriptionDto.getMinYear()<=subscriptionDto.getMinYear()){
            Subscription newSubscription = convertToEntity(subscriptionDto, user);
            newSubscription.setId(subscriptionId);
            subscriptionRepository.save(newSubscription);
            return subscriptionDto;
        }else{
            throw new MingreaterthanMaxException("Maximum value must be greater than minimum value");
        }

    }

    @Override
    public void deleteSubscription(Long userId, Long id) throws SubscriptionNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId).get();
        if (user == null) throw new UserNotFoundException("This user not found");
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getId()==userId).collect(Collectors.toList());
        Optional<Subscription> subscription = subscriptions.stream()
                .filter(s -> s.getId() == id).findAny();
        if (subscription.get() == null) throw new SubscriptionNotFoundException("This subscription is not found");
        subscriptionRepository.delete(subscription.get());
    }

    @Override
    public List<SubscriptionListDto> getAllSubscriptions(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).get();
        System.out.println("AAAAAAAAAAAAAA");
        System.out.println(user);
        if (user == null) throw new UserNotFoundException("This user not found");
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getId()==userId).collect(Collectors.toList());
        for (Subscription subscription : subscriptions) {
            subscriptionListDtos.add(new SubscriptionListDto(subscription));
        }
        return subscriptionListDtos;
    }

    @Override
    public SubscriptionDto getSubscription(Long userId, Long id) throws SubscriptionNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId).get();
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getId()==userId).collect(Collectors.toList());
        if (user == null) throw new UserNotFoundException("This user not found");

        Optional<Subscription> subscription = subscriptions.stream()
                .filter(s -> s.getId() == id).findAny();
        if (subscription.get() == null) throw new SubscriptionNotFoundException("Can't find such listing");
        return new SubscriptionDto(subscription.get());
    }

    @Override
    public List<SubscriptionListDto> getUserSubscriptions(String username) {
        User user = userRepository.findUserByUsername(username);
        List<Subscription> subscriptions=subscriptionRepository.findAll().stream()
                .filter(s->s.getUser().getUsername()==username).collect(Collectors.toList());
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscriptionListDtos.add(new SubscriptionListDto(subscription));
        }
        return subscriptionListDtos;

    }

    public Subscription convertToEntity(SubscriptionDto subscriptionDto, User user) {
        Make make = makeRepository.findById(subscriptionDto.getMakeId()).get();
        Model model = modelRepository.findById(subscriptionDto.getModelId()).get();
        City city = cityRepository.findById(subscriptionDto.getCityId()).get();
        Subscription subscription = DtoHelper.convertSubscriptionDtoToEntity(subscriptionDto, model, city, make, user);
        return subscription;
    }
}
