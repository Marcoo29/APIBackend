package com.uade.tpo.ecommerce.entity.dto;


import com.uade.tpo.ecommerce.entity.enums.Role;
import com.uade.tpo.ecommerce.entity.enums.UserStatus;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String address;  
    private Role role; 
    private UserStatus status;
}
