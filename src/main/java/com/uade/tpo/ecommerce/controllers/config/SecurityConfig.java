package com.uade.tpo.ecommerce.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                // Permitir preflight OPTIONS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/error/**").permitAll()

                // CATEGORY
                .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                .requestMatchers(HttpMethod.GET, "/categories/{categoryId}").permitAll()
                .requestMatchers(HttpMethod.POST, "/categories").hasAuthority("ADMIN")

                // OPERATION
                .requestMatchers(HttpMethod.GET, "/operations").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/operations/{operationId}").authenticated()
                .requestMatchers(HttpMethod.POST, "/operations").hasAuthority("USER")
                .requestMatchers(HttpMethod.POST, "/operations/{operationId}/details").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, "/operations/{operationId}/update").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/operations").authenticated()

                // OPERATIONDETAIL
                .requestMatchers(HttpMethod.GET, "/operationDetail/{operationDetailId}").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/operationDetail/{operationDetailId}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/operationDetail/operationDetail").hasAuthority("ADMIN")

                // PRODUCT
                .requestMatchers(HttpMethod.GET, "/products").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/{productId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/stock/{productId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/by-category/{categoryId}").permitAll()
                .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/{productId}/update").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/all").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/products").hasAuthority("ADMIN")

                // USER
                .requestMatchers(HttpMethod.GET, "/users").permitAll() 
                .requestMatchers(HttpMethod.GET, "/users/{userId}").authenticated()
                .requestMatchers(HttpMethod.POST, "/users").denyAll()
                .requestMatchers(HttpMethod.PUT, "/users/{userId}/update").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/users/{userId}/delete").authenticated()

                // IMAGE
                .requestMatchers(HttpMethod.GET, "/images").permitAll()
                .requestMatchers(HttpMethod.POST, "/images").hasAuthority("ADMIN")

                // Cualquier otro request requiere autenticación
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuración global de CORS
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
