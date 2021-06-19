package com.example.turboaz.dtos;

import com.example.turboaz.models.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingGetDto {
    private Long id;
    private UserDto user;
    private MakeDto make;
    private ModelDto model;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private CityDto city;
    private String gearBox;
    private boolean autoPay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    private String type;
    private String thumbnailUrl;
    private List<CarSpecDto> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;

    public ListingGetDto(Listing listing){
        this.id = listing.getId();
        this.user = new UserDto(listing.getUser());
        this.make = new MakeDto(listing.getModel().getMake());
        this.model = new ModelDto(listing.getModel());
        this.year = listing.getYear();
        this.price = listing.getPrice();
        this.mileage = listing.getMileage();
        this.fuelType = listing.getFuelType().name();
        this.bodyType = listing.getBodyType().name();
        this.color = listing.getColor().name();
        this.city = new CityDto(listing.getCity());
        this.gearBox = listing.getGearBox().name();
        this.autoPay = listing.isAutoPay();
        this.barterOption = listing.isBarterOption();
        this.creditOption =listing.isCreditOption();
        this.leaseOption = listing.isLeaseOption();
        this.cashOption = listing.isCashOption();
        this.description = listing.getDescription();
        this.type = listing.getType().name();
        this.carSpecs = listing.getCarSpecifications().stream().map(c -> new CarSpecDto(c))
                .collect(Collectors.toList());
        this.updatedAt = listing.getUpdatedAt();
        this.isActive = listing.isActive();
    }
}
