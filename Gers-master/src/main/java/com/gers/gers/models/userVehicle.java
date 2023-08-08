package com.gers.gers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class userVehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleType;
    private String vehicleMake;
    @Column(unique = true)
    private  String vehiclePlate;
    private String vehicleEngine;
    private  String vehicleColor;
    private  String user;
}
