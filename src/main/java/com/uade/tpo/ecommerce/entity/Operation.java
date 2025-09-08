package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.enums.OperationStatus;
import com.uade.tpo.ecommerce.entity.enums.PayMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Operation {

    public Operation() {
    }

    public Operation(float total, LocalDateTime date, OperationStatus operationStatus, PayMethod payMethod) {
        this.total = total;
        this.date = date;
        this.operationStatus = operationStatus;
        this.payMethod = payMethod;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float total;

    @Column
    private LocalDateTime date;

    @Column 
    private OperationStatus operationStatus;

    @Column
    private PayMethod payMethod;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "operation_id")
    private List<OperationDetail> details = new ArrayList<>();
}
