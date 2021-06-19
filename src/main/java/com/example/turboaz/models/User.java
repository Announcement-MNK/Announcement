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
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String username;
    private String fullName;
    private String phone;
    private double balance;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Transaction> transactionList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Listing> listingList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Subscription> subscriptions;
}
