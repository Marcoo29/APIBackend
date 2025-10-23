package com.uade.tpo.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findByUserEmail(String email, Pageable pageable);

}
