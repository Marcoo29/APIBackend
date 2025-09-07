package com.uade.tpo.ecommerce.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.enums.OrderStatus;
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

     public Operation createOperation(BigDecimal total, LocalDateTime date, OrderStatus orderStatus, PayMethod payMethod) {
        return operationRepository.save(new Operation(total, date, orderStatus, payMethod));
    }

    public void deleteOperation(Long operationId) {
        Optional<Operation> optionalOperation = operationRepository.findById(operationId);
        if (optionalOperation.isPresent()) {
            Operation operation = optionalOperation.get();
            operation.setOrderStatus(com.uade.tpo.ecommerce.entity.enums.OrderStatus.CANCELLED);
            operationRepository.save(operation);
        }
    }
}

