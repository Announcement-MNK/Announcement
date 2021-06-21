package com.example.turboaz.controllers;

import com.example.turboaz.dtos.SubscriptionDto;
import com.example.turboaz.dtos.SubscriptionListDto;
import com.example.turboaz.exceptions.MingreaterthanMaxException;
import com.example.turboaz.exceptions.SubscriptionMaxCountException;
import com.example.turboaz.exceptions.SubscriptionNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.Subscription;
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.UserRepository;
import com.example.turboaz.services.SubscriptionService;
import com.example.turboaz.services.SubscriptionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubscriptionController {

    SubscriptionService subscriptionService;
    UserRepository userRepository;

    public SubscriptionController(SubscriptionService subscriptionService, UserRepository userRepository) {
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/subscriptions/{userId}")
    public ResponseEntity<List<SubscriptionListDto>> getAll(@PathVariable long userId) throws UserNotFoundException {

        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(userId), HttpStatus.OK);
    }

    @GetMapping("/profile/subscription/{userId}/{id}")
    public ResponseEntity<SubscriptionDto> getSubscription(@PathVariable long userId, @PathVariable long id) throws UserNotFoundException, SubscriptionNotFoundException {
        return new ResponseEntity<>(subscriptionService.getSubscription(userId, id), HttpStatus.OK);
    }

    @PostMapping("/profile/subscriptions/{userId}")
    public ResponseEntity<SubscriptionDto> createSubscription(@PathVariable long userId,@RequestBody SubscriptionDto dto) throws SubscriptionMaxCountException, MingreaterthanMaxException {
        return new ResponseEntity<>(subscriptionService.createSubscription(userId,dto),HttpStatus.OK);
    }
    @PutMapping("/profile/subscriptions/{userId}/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable long userId,@PathVariable long id,@RequestBody SubscriptionDto dto) throws UserNotFoundException, SubscriptionNotFoundException, MingreaterthanMaxException {
        return new ResponseEntity<>(subscriptionService.updateSubscription(userId,id,dto),HttpStatus.OK);
    }
}
