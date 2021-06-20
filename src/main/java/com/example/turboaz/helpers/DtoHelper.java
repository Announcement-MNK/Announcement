package com.example.turboaz.helpers;

import com.example.turboaz.dtos.*;
import com.example.turboaz.enums.*;
import com.example.turboaz.models.*;
import org.dom4j.rule.Mode;

import java.util.ArrayList;
import java.util.List;

public class DtoHelper {
    public static Listing convertListingCreationDtoToEntity(ListingCreationDto dto,
                                                            Model model, City city,
                                                            User user,
                                                            List<CarSpecification> carSpecifications) {
        Listing listing = new Listing();
        listing.setBodyType(BodyType.valueOf(dto.getBodyType()));
        listing.setCity(city);
        listing.setUser(user);
        listing.setModel(model);
        listing.setColor(Color.valueOf(dto.getColor()));
        listing.setPrice(dto.getPrice());
        listing.setYear(dto.getYear());
        listing.setMileage(dto.getMileage());
        listing.setGearBox(GearBox.valueOf(dto.getGearBox()));
        listing.setFuelType(FuelType.valueOf(dto.getFuelType()));
        listing.setAutoPay(dto.isAutoPay());
        listing.setBarterOption(dto.isBarterOption());
        listing.setLeaseOption(dto.isLeaseOption());
        listing.setCashOption(dto.isCashOption());
        listing.setCreditOption(dto.isCreditOption());
        listing.setDescription(dto.getDescription());
        listing.setType(ListingType.valueOf(dto.getType()));
        listing.setCarSpecifications(carSpecifications);
        return listing;
    }

    public static List<ListingListDto> convertToListingListDto(List<Listing> listings) {
        List<ListingListDto> listingListDtos = new ArrayList<>();
        listings.forEach(l -> listingListDtos.add(new ListingListDto(l)));
        return listingListDtos;
    }

    public static Subscription convertSubscriptionDtoToEntity(SubscriptionDto dto,
                                                              Model model, City city, Make make, User user) {
        Subscription subscription = new Subscription();
        subscription.setBodyType(BodyType.valueOf(dto.getBodyType()));
        subscription.setCity(city);
        subscription.setModel(model);
        subscription.setColor(Color.valueOf(dto.getColor()));
        subscription.setMinYear(dto.getMinYear());
        subscription.setMaxYear(dto.getMaxYear());
        subscription.setMinPrice(dto.getMinPrice());
        subscription.setMaxPrice(dto.getMaxPrice());
        subscription.setMake(make);
        subscription.setHasLoan(dto.isLoanOption());
        subscription.setHasCash(dto.isCashOption());
        subscription.setHasLease(dto.isLeaseOption());
        subscription.setFuelType(FuelType.valueOf(dto.getFuelType()));
        subscription.setColor(subscription.getColor());
        subscription.setUser(user);
        return subscription;
    }

    public static List<SubscriptionListDto> convertToSubscriptionListDto(List<Subscription> subscriptions) {
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();
        subscriptions.forEach(s -> subscriptionListDtos.add(new SubscriptionListDto(s)));
        return subscriptionListDtos;
    }
    public static Transaction convertTransactionDtoToEntity(TransactionDto dto, User user,Listing listing){
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setUser(user);
        transaction.setListing(listing);
        transaction.setCreatedAt(dto.getCreatedAt());
        transaction.setDeleted(dto.isDeleted());
        return transaction;
    }
}
