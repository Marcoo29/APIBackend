package com.uade.tpo.ecommerce.entity.dto;

import com.uade.tpo.ecommerce.entity.enums.ProductStatus;


import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private float price;
    private String manufacturer;
    private int stock;
    private String description;
    private String fitFor;
    private ProductStatus productStatus;
    private Long categoryId;
}
