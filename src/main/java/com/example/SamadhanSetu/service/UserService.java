package com.example.SamadhanSetu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SamadhanSetu.entity.User;
import com.example.SamadhanSetu.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo ur;
	
	public User registerUser(User user) {
        // Check if username or email already exists
        if (ur.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        if (ur.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        
        // In a real application, you should hash the password here
        // For now, we'll store it as plain text (NOT recommended for production)
        return ur.save(user);
    }
	
	public User authenticateUser(String username, String password) {
        Optional<User> userOpt = ur.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // In a real application, you should compare hashed passwords
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
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
