package com.uade.tpo.ecommerce.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.uade.tpo.ecommerce.entity.OrderDetail;
import com.uade.tpo.ecommerce.repository.OrderDetailRepository;
 
@Service
public class OrderDetailServiceImpl {
 
    @Autowired
    private OrderDetailRepository orderDetailRepository;
 
    public OrderDetail getOrdersDetailById(Long id){
        OrderDetail orderDetail = orderDetailRepository.getReferenceById(id);
        return orderDetail;
    }
 
    public Boolean deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
        return true;
    }
 
    public OrderDetail updateOrderDetail(OrderDetail newOrderDetail) {
        if (!orderDetailRepository.existsById(newOrderDetail.getId())) {
            throw new RuntimeException("OrderDetail no encontrado");
        }
        return orderDetailRepository.save(newOrderDetail);
    }
}