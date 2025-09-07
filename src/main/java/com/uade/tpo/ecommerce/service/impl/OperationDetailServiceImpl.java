package com.uade.tpo.ecommerce.service.impl;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.uade.tpo.ecommerce.entity.OperationDetail;
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
 
    public void deleteOperationDetail(Long id) {
        operationDetailRepository.deleteById(id);
    }
 
    public Optional<OperationDetail> updateOperationDetail(OperationDetail newOperationDetail) {
        if (!operationDetailRepository.existsById(newOperationDetail.getId())) {
            throw new RuntimeException("OperationDetail no encontrado");
        }
        return Optional.of(operationDetailRepository.save(newOperationDetail));
    }
}