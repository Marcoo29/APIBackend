package com.uade.tpo.ecommerce.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.enums.PayMethod;
import com.uade.tpo.ecommerce.repository.OperationRepository;
import com.uade.tpo.ecommerce.service.inter.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
    
    @Autowired
    private OperationRepository operationRepository;

    public Page<Operation> getOperations(PageRequest pageable) {
        return operationRepository.findAll(pageable);
    }

    public Optional<Operation> getOperationById(Long operationId) {
        return operationRepository.findById(operationId);
    }

     public Operation createOperation(BigDecimal total, LocalDateTime fecha, PayMethod metodoPago) {
        return operationRepository.save(new Operation(total, fecha, metodoPago));
    }
}