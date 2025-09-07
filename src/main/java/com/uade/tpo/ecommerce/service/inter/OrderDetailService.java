package com.uade.tpo.ecommerce.service.inter;
 
import java.util.Optional;
 
import com.uade.tpo.ecommerce.entity.OrderDetail;
 
public interface OrderDetailService {
 
    public Optional<OrderDetail> getOrderDetailById(Long id);
 
    public Optional<Boolean> deleteOrderDetail(Long id);
 
    public Optional<OrderDetail> updateOrderDetail(OrderDetail newOrderDetail);
}