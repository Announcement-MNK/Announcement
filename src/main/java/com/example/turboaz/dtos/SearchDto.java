package com.example.turboaz.dtos;

    import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private long modelId;
    private long cityId;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
    private int fuelType;
    private int bodyType;
    private boolean hasLoan;
    private boolean hasLease;
    private boolean hasCash;
}
