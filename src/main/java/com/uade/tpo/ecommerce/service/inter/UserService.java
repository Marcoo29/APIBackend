package com.uade.tpo.ecommerce.service.inter;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserRequest;
import com.uade.tpo.ecommerce.exceptions.UserDuplicateException;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    public Page<User> getUsers(PageRequest pageRequest);

    public Optional<User> getUserById(Long userId);

    public User createUser(UserRequest userRequest) throws UserDuplicateException;

    public List<User> getAllUsers();

    public User deleteUser(Long userId);

    public User updateUser(Long userId, UserRequest userRequest);
}
