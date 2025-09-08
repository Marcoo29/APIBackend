package com.uade.tpo.ecommerce.entity.dto;


import lombok.Data;

@Data
public class OperationDetailRequest {
    private Long operationId;
    private Long productId;
    private int quantity;
    
}
