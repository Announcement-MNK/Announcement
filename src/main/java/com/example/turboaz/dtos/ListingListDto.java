package com.example.turboaz.dtos;

import com.example.turboaz.models.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingListDto {
    private Long id;
    private String makeName;
    private String modelName;
    private String thumbnailUrl;
    private String cityName;
    private Integer price;
    private Integer mileage;
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
