package com.uade.tpo.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.OperationDetail;

@Repository
public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long> {

}