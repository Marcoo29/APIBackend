package com.uade.tpo.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.OperationDetail;

@Repository
public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long> {
    List<OperationDetail> findByOperationId(Long operationId);

}