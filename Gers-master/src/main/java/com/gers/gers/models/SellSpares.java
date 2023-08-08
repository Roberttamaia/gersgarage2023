package com.gers.gers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SellSpares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

   private String Spare;

   private  String user;
   private BigDecimal amount;
   private  int  orders;
   private int quantity;
   private BigDecimal totalAmount;
}
