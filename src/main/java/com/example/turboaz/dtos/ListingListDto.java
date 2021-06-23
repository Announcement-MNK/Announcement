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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingListDto {
    private Long id;
    @NotNull(message = "Field cannot be null")
    private String makeName;
    @NotNull(message = "Field cannot be null")
    private String modelName;
    @NotNull(message = "Field cannot be null")
    private String thumbnailUrl;
    @NotNull(message = "Field cannot be null")
    private String cityName;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Price cannot be negative")
    private Integer price;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Mileage cannot be negative")
    private Integer mileage;
    @NotNull(message = "Field cannot be null")
    @Min(value = 1800,message = "Given year is too old")
    @Max(value = 2022, message = "Year cannot be greater than now")
    private Integer year;
    private LocalDateTime updatedAt;

    public ListingListDto(Listing listing){
        this.id = listing.getId();
        this.makeName = listing.getModel().getMake().getName();
        this.modelName = listing.getModel().getName();
        this.thumbnailUrl = null;
        this.cityName = listing.getCity().getName();
        this.price = listing.getPrice();
        this.mileage  = listing.getMileage();
        this.year = listing.getYear();
        this.updatedAt = listing.getUpdatedAt();
    }

}
