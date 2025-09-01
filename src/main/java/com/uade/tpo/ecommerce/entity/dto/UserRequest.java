package com.uade.tpo.ecommerce.entity.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String address;  
    private ArrayList<String> role; 
}
