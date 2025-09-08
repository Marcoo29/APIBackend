package com.uade.tpo.ecommerce.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.tpo.ecommerce.entity.enums.Role;
import com.uade.tpo.ecommerce.entity.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private String address;  

    @Column
    private Role role; 

    @Column
    private UserStatus status = UserStatus.ACTIVE; // esta activo por default

    @OneToMany(mappedBy = "user") // user es la FK para vincular la tabla User con Order
    @JsonIgnore
    private List<Operation> operations;

}