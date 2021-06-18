package com.example.turboaz.models;

import com.example.turboaz.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "listings")

public class Listing {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    private int year;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;
    private double mileage;
    private boolean hasBarter;
    private int price;
    private double engine;
    private FuelType fuelType;
    private GearBox gearBox;
    private ListingType type;
    private BodyType bodyType;
    private Color color;
    private boolean isDisabled;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Equipment> equipments;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private List<Image> images;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
