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

public class Equipment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private long id;
    private String name;
    @ManyToMany(mappedBy = "equipments")
    private List<Listing> listingList;
}
