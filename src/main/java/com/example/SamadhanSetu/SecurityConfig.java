package com.example.SamadhanSetu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS
                .cors(Customizer.withDefaults())
                // Disable CSRF for APIs
                .csrf(csrf -> csrf.disable())
                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/videos/all", "/api/videos/category/", "/api/videos/latest").permitAll()
                        .requestMatchers("/api/stories/all", "/api/stories/category/", "/api/stories/latest").permitAll()
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()

                        // ✅ Public Issue Reporting API
                        .requestMatchers(HttpMethod.POST, "/api/report-issue").permitAll()

                        // Admin-only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/videos/upload").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/videos/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/videos/").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/stories/upload").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/stories/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/stories/").hasRole("ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                // Basic authentication (you can replace later with JWT/session)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Global CORS configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Explicitly allow your React app
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization")); // if you return JWT tokens
        config.setAllowCredentials(true); // important if using cookies or auth headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/", config); // ✅ apply to all endpoints
        return source;
    }
}