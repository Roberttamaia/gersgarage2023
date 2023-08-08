package com.gers.gers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private  String serviceType;
        @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate bookingDate;
    private String vehicle;
    private String description;
    private String mechanic;
    private String status = "new";
    private BigDecimal amount;
    private String paid = "notPaid";
    private String orderName;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate pickUpDate;
}
