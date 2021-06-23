package com.example.turboaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MailSessionDefinition;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingCreationDto {
    private Long makeId;
    private Long modelId;
    @NotNull(message = "Field cannot be null")
    @Min(value = 1800,message = "Given year is too old")
    @Max(value = 2022, message = "Year cannot be greater than now")
    private int year;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Price cannot be negative")
    private int price;
    @NotNull(message = "Field cannot be null")
    @PositiveOrZero(message = "Price cannot be negative")
    private int mileage;
    @NotNull(message = "Field cannot be null")
    private String fuelType;
    @NotNull(message = "Field cannot be null")
    private String bodyType;
    @NotNull(message = "Field cannot be null")
    private String color;
    private Long cityId;
    @NotNull(message = "Field cannot be null")
    private String gearBox;
    private boolean autoPay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    @NotNull(message = "Field cannot be null")
    private String description;
    @NotNull(message = "Field cannot be null")
    private String type;
    private List<Long> carSpecIds;
}
