package com.example.turboaz.models;

import com.example.turboaz.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;
    private int year;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    private int mileage;
    private int price;
    private FuelType fuelType;
    private GearBox gearBox;
    private ListingType type;
    private BodyType bodyType;
    private Color color;
    private boolean autoPay;
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CarSpecification> carSpecifications;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private List<Image> images;
    private boolean isActive;
    private LocalDateTime updatedAt;
}
