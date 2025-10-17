package com.uade.tpo.ecommerce.service.inter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.controllers.auth.AuthenticationRequest;
import com.uade.tpo.ecommerce.controllers.auth.AuthenticationResponse;
import com.uade.tpo.ecommerce.controllers.auth.RegisterRequest;
import com.uade.tpo.ecommerce.controllers.config.JwtService;
import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .name(request.getName())
                                .lastname(request.getLastname())
                                .email(request.getEmail())
                                .address(request.getAddress())
                                .role(request.getRole())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));

                var user = repository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .name(user.getName())
                                .build();
        }
}
