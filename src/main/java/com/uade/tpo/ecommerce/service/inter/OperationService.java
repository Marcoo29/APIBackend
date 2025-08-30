package com.uade.tpo.ecommerce.service.inter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.enums.MetodoPago;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface OperationService {
    public Page<Operation> getOperations(PageRequest pageRequest);

    public Optional<Operation> getOperationById(Long operationId);

    public Operation createOperation(BigDecimal total, LocalDateTime fecha, MetodoPago metodoPago);
}