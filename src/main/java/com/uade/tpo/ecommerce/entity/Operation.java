package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Operation {

    public Operation() {
    }

    public Operation(BigDecimal total, LocalDateTime date, PayMethod payMethod) {
        this.total = total;
        this.date = date;
        this.payMethod = payMethod;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal total;

    @Column
    private LocalDateTime date;

    @Column
    private PayMethod payMethod;

}
