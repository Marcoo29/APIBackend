package com.uade.tpo.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select c from OrderDetail c where c.id = ?1")
    List<OrderDetail> getOrdersDetailById(Long id);

    @Query(value = "select c from OrderDetail c where c.order_id = ?1")
    List<OrderDetail> getOrdersDetailByOrderId(Long id); //Trae todos los detalles de una orden
}