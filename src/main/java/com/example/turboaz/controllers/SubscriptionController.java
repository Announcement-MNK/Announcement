package com.example.turboaz.controllers;

import com.example.turboaz.dtos.SubscriptionDto;
import com.example.turboaz.dtos.SubscriptionListDto;
import com.example.turboaz.dtos.UserDto;
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

    @GetMapping("/profile/subscriptions")
    public ResponseEntity<List<SubscriptionListDto>> getAll(@RequestAttribute("user")UserDto userDto) throws UserNotFoundException {

        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(userDto.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/profile/subscription/{id}")
    public ResponseEntity<SubscriptionDto> getSubscription(@RequestAttribute("user")UserDto userDto, @PathVariable long id) throws UserNotFoundException, SubscriptionNotFoundException {
        return new ResponseEntity<>(subscriptionService.getSubscription(userDto.getUsername(), id), HttpStatus.OK);
    }

    @PostMapping("/profile/subscriptions")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestAttribute("user")UserDto userDto,@RequestBody SubscriptionDto dto) throws SubscriptionMaxCountException, MingreaterthanMaxException {
        return new ResponseEntity<>(subscriptionService.createSubscription(userDto.getUsername(),dto),HttpStatus.OK);
    }
    @PutMapping("/profile/subscriptions/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@RequestAttribute("user")UserDto userDto,@PathVariable long id,@RequestBody SubscriptionDto dto) throws UserNotFoundException, SubscriptionNotFoundException, MingreaterthanMaxException {
        return new ResponseEntity<>(subscriptionService.updateSubscription(userDto.getUsername(),id,dto),HttpStatus.OK);
    }
}
