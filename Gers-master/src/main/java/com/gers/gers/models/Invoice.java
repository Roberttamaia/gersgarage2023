package com.gers.gers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private int orderNo;
    private BigDecimal totalAmount;

}
