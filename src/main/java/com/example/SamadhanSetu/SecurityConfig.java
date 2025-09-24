package com.example.SamadhanSetu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF disable
        http.csrf(csrf -> csrf.disable());

        // Authorization rules
        http.authorizeHttpRequests(auth -> auth
                // Public endpoints (anyone can read)
                .requestMatchers("/api/videos/all", "/api/videos/category/**", "/api/videos/latest").permitAll()
                .requestMatchers("/api/stories/all", "/api/stories/category/**", "/api/stories/latest").permitAll()

                // Admin-only endpoints
                .requestMatchers(HttpMethod.POST, "/api/videos/upload").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/videos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/videos/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/stories/upload").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/stories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/stories/**").hasRole("ADMIN")

                // Any other request requires authentication
                .anyRequest().authenticated()
        );

        // HTTP Basic Authentication
        http.httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
