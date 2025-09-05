package com.uade.tpo.ecommerce.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private float price;
    private String fabricante;
    private int stock;
    private String description;
    private String fitFor;
}
