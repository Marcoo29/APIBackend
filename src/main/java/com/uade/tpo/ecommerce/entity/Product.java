package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.enums.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private String manufacturer;

    @Column
    private int stock;

    @Column
    private String description;

    @Column
    private String fitFor;

    @Column
    private ProductStatus productStatus;

}
