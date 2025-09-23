//package com.example.SamadhanSetu.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.SamadhanSetu.dao.Entity.User;
//import com.example.SamadhanSetu.dao.Repository.UserRepo;
//
//@Service
//public class UserService {
//
//	@Autowired
//	private UserRepo ur;
//
//	public User registerUser(User user) {
//        // Check if username or email already exists
//        if (ur.existsByUsername(user.getUsername())) {
//            throw new RuntimeException("Username already exists!");
//        }
//        if (ur.existsByEmail(user.getEmail())) {
//            throw new RuntimeException("Email already exists!");
//        }
//
//        // In a real application, you should hash the password here
//        // For now, we'll store it as plain text (NOT recommended for production)
//        return ur.save(user);
//    }
//
//	public User authenticateUser(String username, String password) {
//        Optional<User> userOpt = ur.findByUsername(username);
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            // In a real application, you should compare hashed passwords
//            if (user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//	public Optional<User> findByUsername(String username) {
//        return ur.findByUsername(username);
//    }
//
//    public Optional<User> findById(Long id) {
//        return ur.findById(id);
//    }
//
//    public boolean isUsernameExists(String username) {
//        return ur.existsByUsername(username);
//    }
//
//    public boolean isEmailExists(String email) {
//        return ur.existsByEmail(email);
//    }
//
//}
package com.example.SamadhanSetu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SamadhanSetu.dao.Entity.User;
import com.example.SamadhanSetu.dao.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo ur;

    // Register a new user
    public User registerUser(User user) {
        // Check if username already exists
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new RuntimeException("Username is required!");
        }
        if (ur.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        // Check if email already exists
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required!");
        }
        if (ur.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // In a real application, hash the password here
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required!");
        }

        return ur.save(user);
    }

    // Authenticate user by username and password
    public User authenticateUser(String username, String password) {
        Optional<User> userOpt = ur.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // For production, compare hashed passwords
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
