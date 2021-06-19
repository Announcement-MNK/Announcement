package com.example.turboaz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipments")

public class CarSpecification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private long id;
    private String name;
    @ManyToMany(mappedBy = "carSpecifications")
    private List<Listing> listingList;
}
