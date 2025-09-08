package com.uade.tpo.ecommerce.service.impl;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.repository.UserRepository;
import com.uade.tpo.ecommerce.service.inter.UserService;
import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserRequest;
import com.uade.tpo.ecommerce.exceptions.UserDuplicateException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(PageRequest pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

   
    public User createUser(UserRequest userRequest) throws UserDuplicateException {

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());    
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setRole(userRequest.getRole());

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long userId, UserRequest userRequest){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (userRequest.getUsername() != null) user.setUsername(userRequest.getUsername());
        if (userRequest.getName() != null) user.setName(userRequest.getName());
        if (userRequest.getLastname() != null) user.setLastname(userRequest.getLastname());
        if (userRequest.getAddress() != null) user.setAddress(userRequest.getAddress());
        if (userRequest.getRole() != null) user.setRole(userRequest.getRole());

        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
         Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(com.uade.tpo.ecommerce.entity.enums.UserStatus.INACTIVE);
            return (userRepository.save(user));
        }

        throw new RuntimeException("Usuario no encontrado");
    }
}
