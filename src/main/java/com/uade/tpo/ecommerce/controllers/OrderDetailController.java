package com.uade.tpo.ecommerce.controllers;
 
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.uade.tpo.ecommerce.entity.OrderDetail;
import com.uade.tpo.ecommerce.service.inter.OrderDetailService;
 
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
 
    @Autowired
    OrderDetailService orderDetailService;
 
    @GetMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long orderDetailId) {
        Optional<OrderDetail> result = orderDetailService.getOrderDetailById(orderDetailId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }
 
    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Boolean> deleteOrderDetail(@PathVariable Long orderDetailId) {
        Optional<Boolean> result = orderDetailService.deleteOrderDetail(orderDetailId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }
 
    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable OrderDetail newOrderDetail) {
        Optional<OrderDetail> result = orderDetailService.updateOrderDetail(newOrderDetail);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }
}
 