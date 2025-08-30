package com.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Operation;
import com.uade.tpo.ecommerce.entity.dto.OperationRequest;
import com.uade.tpo.ecommerce.service.inter.OperationService;


@RestController
@RequestMapping("operations")

public class OperationsController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public ResponseEntity<Page<Operation>> getOperations(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(operationService.getOperations(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(operationService.getOperations(PageRequest.of(page, size)));
    }

    @GetMapping("/{operationId}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long operationId) {
        Optional<Operation> result = operationService.getOperationById(operationId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createOperation(@RequestBody OperationRequest operationRequest){
            Operation result = operationService.createOperation(
                operationRequest.getTotal(),
                operationRequest.getDate(),
                operationRequest.getPayMethod()
                
                );
        return ResponseEntity.created(URI.create("/operations/" + result.getId())).body(result);
    }
}
