package com.uade.tpo.ecommerce.controllers;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.OperationDetail;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.OperationDetailRequest;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.service.inter.OperationDetailService;
 
@RestController
@RequestMapping("operationDetail")
public class OperationDetailController {
 
    @Autowired
    OperationDetailService operationDetailService;
 
    @GetMapping("/{operationDetailId}")
    public ResponseEntity<OperationDetail> getOperationDetailById(@PathVariable Long operationDetailId) {
        Optional<OperationDetail> result = operationDetailService.getOperationDetailById(operationDetailId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{operationId}/details")
    public ResponseEntity<List<OperationDetail>> getOperationDetails(@PathVariable Long operationId) {
        List<OperationDetail> details = operationDetailService.getOperationDetailsByOperationId(operationId);
        return ResponseEntity.ok(details);
}
 
    @DeleteMapping("/{operationDetailId}")
    public void deleteOperationDetail(@PathVariable Long operationDetailId) {
        operationDetailService.deleteOperationDetail(operationDetailId);
    }
 
    @PutMapping("/{operationDetailId}")
    public ResponseEntity<OperationDetail> updateOperationDetail(@PathVariable Long operationDetailId,  @RequestBody OperationDetailRequest operationDetailRequest) {
        OperationDetail updatedOperationDetail = operationDetailService.updateOperationDetail(operationDetailId, operationDetailRequest);
        return ResponseEntity.ok(updatedOperationDetail);
    }
}
 