package com.uade.tpo.ecommerce.controllers.auth;

import com.uade.tpo.ecommerce.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String address;
    private Role role;
}

