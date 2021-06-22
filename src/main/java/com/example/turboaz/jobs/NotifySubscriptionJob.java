package com.example.turboaz.jobs;

import com.example.turboaz.models.Listing;
import com.example.turboaz.services.EmailService;
import com.example.turboaz.services.ListingService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class NotifySubscriptionJob implements Job {

    ListingService listingService;
    EmailService emailService;

    public NotifySubscriptionJob(ListingService listingService, EmailService emailService){
        this.listingService = listingService;
        this.emailService = emailService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Listing> listings = listingService.getAllTomorrowExpiredListings();
        listings.stream().forEach(l -> emailService.sendMail(l.getUser().getEmail(),
                "Turbo AZ", "Your announcement will expire tomorrow"));
    }
}
