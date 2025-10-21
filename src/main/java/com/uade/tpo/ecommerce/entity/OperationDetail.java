package com.uade.tpo.ecommerce.entity;
 

import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
 
@Entity
@Data
@Table
public class OperationDetail {
 
    public OperationDetail() {
 
    }

    public OperationDetail(int quantity, float unitaryPrice, float subtotal, PayMethod payMethod) {
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.subtotal = subtotal;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
   
    @Column
    public int quantity;
 
    @Column
    public float unitaryPrice;
 
    @Column
    public float subtotal;
 
    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    @ManyToOne
    private Product product;
   
}