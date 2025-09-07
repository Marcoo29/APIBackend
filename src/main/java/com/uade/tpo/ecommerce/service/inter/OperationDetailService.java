package com.uade.tpo.ecommerce.service.inter;
 
import java.util.Optional;
 
import com.uade.tpo.ecommerce.entity.OperationDetail;
 
public interface OperationDetailService {
 
    public Optional<OperationDetail> getOperationDetailById(Long id);
 
    public void deleteOperationDetail(Long id);
 
    public Optional<OperationDetail> updateOperationDetail(OperationDetail newOperationDetail);
}