package com.uade.tpo.ecommerce.entity.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import lombok.Data;

@Data
public class OperationRequest {
    private Long userId;
    private List<OperationDetailRequest> operationDetails;
    private PayMethod payMethod;
}
