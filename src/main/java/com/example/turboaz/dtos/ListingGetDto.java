package com.example.turboaz.dtos;

import com.example.turboaz.models.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
    @NotNull(message = "Field cannot be null")
    @Min(value = 1800,message = "Given year is too old")
    @Max(value = 2022, message = "Year cannot be greater than now")
    private int year;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Price cannot be negative")
    private int price;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Mileage cannot be negative")
    private int mileage;
    @NotNull(message = "Field cannot be null")
    private String fuelType;
    @NotNull(message = "Field cannot be null")
    private String bodyType;
    @NotNull(message = "Field cannot be null")
    private String color;
    @NotNull(message = "Field cannot be null")
    private CityDto city;
    @NotNull(message = "Field cannot be null")
    private String gearBox;
    @NotNull(message = "Field cannot be null")
    private boolean autoPay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    @NotNull(message = "Field cannot be null")
    private String description;
    @NotNull(message = "Field cannot be null")
    private String type;
    @NotNull(message = "Field cannot be null")
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
