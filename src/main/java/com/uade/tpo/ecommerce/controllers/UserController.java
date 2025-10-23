package com.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String email) {
        Optional<User> result = userService.getUserByEmail(email);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest)
            throws UserDuplicateException {
        User result = userService.createUser(userRequest);
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User updatedUser = userService.updateUser(userId, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        User deletedUser = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedUser);
    }

}
