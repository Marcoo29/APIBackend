package com.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserRequest;
import com.uade.tpo.ecommerce.exceptions.UserDuplicateException;
import com.uade.tpo.ecommerce.service.inter.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> result = userService.getUserById(userId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest)
            throws UserDuplicateException {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getUsername());
        user.setLastname(userRequest.getLastname());
        user.setAddress(userRequest.getAddress());
        user.setRole(userRequest.getRole());

        User result = userService.createUser(user);

        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

}
