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
import com.uade.tpo.ecommerce.exceptions.UserDuplicateException;
import com.uade.tpo.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Registro de usuario
    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new UserDuplicateException("ERROR. El nombre de usuario ya existe");
        }

        if (repository.existsByEmail(request.getEmail())) {
            throw new UserDuplicateException("ERROR. El correo electrónico ya está registrado");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .role(request.getRole()) // asegurarse de recibir rol al registrar
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole()) // incluir rol
                .build();
    }

    // Autenticación de usuario
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1️⃣ Autenticar con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        // 2️⃣ Traer el usuario
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3️⃣ Generar token JWT
        var jwtToken = jwtService.generateToken(user);

        // 4️⃣ Devolver respuesta con rol
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .name(user.getName())
                .role(user.getRole()) 
                .email(user.getEmail())
                .build();
    }
}
