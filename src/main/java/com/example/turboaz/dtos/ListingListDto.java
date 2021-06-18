package com.example.turboaz.dtos;

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
}
