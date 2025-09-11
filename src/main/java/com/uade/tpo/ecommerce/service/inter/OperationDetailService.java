package com.uade.tpo.ecommerce.service.inter;
 
import java.util.List;
import java.util.Optional;
 
import com.uade.tpo.ecommerce.entity.OperationDetail;
import com.uade.tpo.ecommerce.entity.dto.OperationDetailRequest;
 
public interface OperationDetailService {
 
    public Optional<OperationDetail> getOperationDetailById(Long id);

    public List<OperationDetail> getOperationDetailsByOperationId(Long operationId);

    public void deleteOperationDetail(Long id);
 
    public OperationDetail updateOperationDetail(Long operationDetailId, OperationDetailRequest operationDetailRequest);
}