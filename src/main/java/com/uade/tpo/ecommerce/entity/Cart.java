package com.uade.tpo.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Cart {

    public Cart() {
    }

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //falta la FK idUsuario
}

