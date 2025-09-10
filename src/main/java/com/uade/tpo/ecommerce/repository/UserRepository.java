package com.uade.tpo.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.username = ?1")
    User findByUsername(String username);

    Optional<User> findByEmail(String email);
}
