package com.uade.tpo.ecommerce.entity.dto;

import com.uade.tpo.ecommerce.entity.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import lombok.Data;

@Data
public class OperationRequest {
    private int id;
    private BigDecimal total;
    private LocalDateTime date;
    private OrderStatus orderstatus;
    private PayMethod payMethod;
}
