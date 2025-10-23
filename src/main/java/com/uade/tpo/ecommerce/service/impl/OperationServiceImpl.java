package com.uade.tpo.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.OperationDetail;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.OperationRequest;
import com.uade.tpo.ecommerce.entity.enums.OperationStatus;
import com.uade.tpo.ecommerce.repository.OperationRepository;
import com.uade.tpo.ecommerce.repository.ProductRepository;
import com.uade.tpo.ecommerce.service.inter.OperationService;
import com.uade.tpo.ecommerce.service.inter.UserService;

import jakarta.transaction.Transactional;

@Service
public class OperationServiceImpl implements OperationService {
    
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Page<Operation> getOperations(PageRequest pageable) {
        return operationRepository.findAll(pageable);
    }

    public Optional<Operation> getOperationById(Long operationId) {
        return operationRepository.findById(operationId);
    }

    public Page<Operation> getOperationsByEmail(String email, PageRequest pageable) {
        return operationRepository.findByUserEmail(email, pageable);
    }

    @Transactional
    public Operation createOperation(OperationRequest operationRequest) {
        User user = userService.getUserById(operationRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LocalDateTime date = LocalDateTime.now();

        Operation operation = new Operation();
        operation.setUser(user);
        operation.setDate(date);
        operation.setOperationStatus(OperationStatus.IN_PROCESS);
        operation.setPayMethod(operationRequest.getPayMethod());

        for (var detailRequest : operationRequest.getOperationDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int quantity = detailRequest.getQuantity();
            if (product.getStock() < quantity) {
                throw new RuntimeException("Stock insuficiente para " + product.getName());
            }
            product.setStock(product.getStock() - quantity);

            OperationDetail detail = new OperationDetail();
            detail.setOperation(operation);
            detail.setProduct(product);
            detail.setQuantity(detailRequest.getQuantity());
            detail.setUnitaryPrice(product.getPrice());
            detail.setSubtotal(product.getPrice() * detailRequest.getQuantity());

            operation.getDetails().add(detail);
            operation.setTotal(operation.getTotal() + product.getPrice() * detailRequest.getQuantity());
        }

        return operationRepository.save(operation);
    }

    public OperationDetail addProduct(Long operationId, Long productId, int quantity) {
        Operation operation = operationRepository.findById(operationId)
        .orElseThrow(() -> new RuntimeException("Operación no encontrada"));

        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        OperationDetail detail = new OperationDetail();
        detail.setOperation(operation);
        detail.setProduct(product);
        detail.setQuantity(quantity);
        detail.setUnitaryPrice(product.getPrice());

        operation.getDetails().add(detail);
        operation.setTotal(operation.getTotal() + product.getPrice() * quantity);

        operationRepository.save(operation); // cascade guarda el detail
        return detail;
    }

    // public Operation updateOperation(Long operationId, OperationRequest operationRequest){
    //     Operation operation = operationRepository.findById(operationId)
    //         .orElseThrow(() -> new RuntimeException("Operación no encontrada"));

    //     if (operationRequest.getTotal() != 0) operation.setTotal(operationRequest.getTotal());
    //     if (operationRequest.getOperationStatus() != null) operation.setOperationStatus(operationRequest.getOperationStatus());
    //     if (operationRequest.getPayMethod() != null) operation.setPayMethod(operationRequest.getPayMethod());

    //     return operationRepository.save(operation);
    // }

    public Operation deleteOperation(Long operationId) {
         Optional<Operation> optionalOperation = operationRepository.findById(operationId);
        if (optionalOperation.isPresent()) {
            Operation operation = optionalOperation.get();
            operation.setOperationStatus(com.uade.tpo.ecommerce.entity.enums.OperationStatus.CANCELLED);
            return (operationRepository.save(operation));
        }
 
        throw new RuntimeException("Operación no encontrada");
    }
}

