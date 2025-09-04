package com.uade.tpo.ecommerce.entity;

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
@Table(name = "order_detail")
public class OrderDetail {

    public OrderDetail() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column
    public int cantidad;

    @Column
    public float precio_unitario;

    @Column
    public float subtotal = precio_unitario*cantidad;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Order orders_id;
    

}