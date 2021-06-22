package com.example.turboaz.jobs;

import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.Listing;
import com.example.turboaz.services.ListingService;
import com.example.turboaz.services.TransactionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class AutoPaymentJob implements Job {

    ListingService listingService;

    public AutoPaymentJob(TransactionService transactionService, ListingService listingService){
        this.listingService = listingService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
//        List<Listing> listings = listingService.getAllExpiredListings();
//        listings.stream().forEach(l -> {
//            try {
//                listingService.makePaid(l.getUser().getUsername(), l.getId());
//            } catch (ListingNotFoundException e) {
//                e.printStackTrace();
//            } catch (UserNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
