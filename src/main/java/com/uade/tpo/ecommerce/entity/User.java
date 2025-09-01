package com.uade.tpo.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String lastname;

   @Column
    private String address;  

    @Column
    private ArrayList<String> role; 

    @OneToMany(mappedBy = "users") // user es la FK para vincular la tabla User con Order
    private List<Order> orders;

}