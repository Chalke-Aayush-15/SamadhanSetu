package com.example.SamadhanSetu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SamadhanSetu.dao.Entity.User;
import com.example.SamadhanSetu.dao.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo ur;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder

    // Register a new user
    public User registerUser(User user) {
        // Validate username
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new RuntimeException("Username is required!");
        }
        if (ur.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        // Validate email
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required!");
        }
        if (ur.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // Validate password
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required!");
        }

        // ✅ Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return ur.save(user);
    }

    // Authenticate user by username and password
    public User authenticateUser(String username, String rawPassword) {
        Optional<User> userOpt = ur.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // ✅ Compare raw password with encoded password
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user;
            }
        }
        return null; // authentication failed
    }

    public Optional<User> findByUsername(String username) {
        return ur.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return ur.findById(id);
    }

    public boolean isUsernameExists(String username) {
        return ur.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return ur.existsByEmail(email);
    }
}
