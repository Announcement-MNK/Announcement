package com.example.turboaz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")

public class Image {
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
     private Long id;
     private boolean isMain;
     @Lob
     byte[] content;
}
