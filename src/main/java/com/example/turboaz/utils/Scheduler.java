package com.example.turboaz.utils;

import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.Listing;
import com.example.turboaz.repositories.ListingRepository;
import com.example.turboaz.services.EmailService;
import com.example.turboaz.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Scheduler {
    @Autowired
    ListingService listingService;
    @Autowired
    EmailService emailService;

    @Autowired
    ListingRepository listingRepository;

    @Scheduled(cron = "0 55 15 * * ?", zone="Asia/Baku")
    public void expireDateSendMail() throws UserNotFoundException, ListingNotFoundException {
        List<Listing> allExpiredListings = listingRepository.getAllExpireds();
        for(Listing l : allExpiredListings) {
            listingService.makePaid(l.getUser().getUsername(), l.getId());
        }

        List<Listing> allTomorrowExpiredListings = listingRepository.getAllWillExpireds();
        for(Listing l : allTomorrowExpiredListings.stream()
                .filter(l -> l.getUpdatedAt().plusMonths(1).minusDays(1).isEqual( LocalDateTime.now()))
                .collect(Collectors.toList())) {
            emailService.sendMail(l.getUser().getEmail(), "Hello", "Your listing will expire tomorrow");
        }
    }
}