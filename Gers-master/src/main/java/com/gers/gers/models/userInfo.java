package com.gers.gers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class userInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(unique = true)
     private String username;
     private  String firstname;
     private String lastname;
    @Column(unique = true)
      private  String email;
     private  String phone;
      private  String password;
    private String roles = "ROLE_USER";


}
