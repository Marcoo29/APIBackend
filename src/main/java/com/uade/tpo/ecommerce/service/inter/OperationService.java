package com.uade.tpo.ecommerce.service.inter;

import java.util.Optional;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.OperationDetail;
import com.uade.tpo.ecommerce.entity.dto.OperationRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface OperationService {
    public Page<Operation> getOperations(PageRequest pageRequest);

    public Optional<Operation> getOperationById(Long operationId);

    public Operation createOperation(OperationRequest operationRequest);

    public Page<Operation> getOperationsByEmail(String email, PageRequest pageable);

    public Operation saveOperation(Operation operation);

    public OperationDetail addProduct(Long operationId, Long productId, int quantity);

    public Operation deleteOperation(Long operationId);
}