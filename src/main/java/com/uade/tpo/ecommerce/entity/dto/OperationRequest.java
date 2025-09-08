package com.uade.tpo.ecommerce.entity.dto;

import com.uade.tpo.ecommerce.entity.enums.OperationStatus;
import java.time.LocalDateTime;

import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import lombok.Data;

@Data
public class OperationRequest {
    private int id;
    private Long userId;
    private float total;
    private LocalDateTime date;
    private OperationStatus operationStatus;
    private PayMethod payMethod;
}
