package com.uade.tpo.ecommerce.service.impl;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.OperationDetail;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.OperationDetailRequest;
import com.uade.tpo.ecommerce.repository.OperationDetailRepository;
import com.uade.tpo.ecommerce.service.inter.OperationDetailService;
 
@Service
public class OperationDetailServiceImpl implements OperationDetailService {

 
    @Autowired
    private OperationDetailRepository operationDetailRepository;
 
    public Optional<OperationDetail> getOperationDetailById(Long id){
        Optional<OperationDetail> operationDetail = operationDetailRepository.findById(id);
        return operationDetail;
    }

    public List<OperationDetail> getOperationDetailsByOperationId(Long operationId) {
    return operationDetailRepository.findByOperationId(operationId);
}
 
    public void deleteOperationDetail(Long id) {
        operationDetailRepository.deleteById(id);
    }
 
    public OperationDetail updateOperationDetail(Long operationDetailId, OperationDetailRequest operationDetailRequest) {
        OperationDetail operationDetail = operationDetailRepository.findById(operationDetailId)
            .orElseThrow(() -> new RuntimeException("Operation Detail no encontrado"));

        if (operationDetailRequest.getQuantity() != 0) operationDetail.setQuantity(operationDetailRequest.getQuantity());

        return operationDetailRepository.save(operationDetail);
    }
}