package com.example.SamadhanSetu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SamadhanSetu.dao.Entity.User;
import com.example.SamadhanSetu.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5174"}, allowCredentials = "true")
public class MainController {

	@Autowired
	private UserService us;
	
	// Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("+user", new User());
        return "register";
    }
    
    // Handle registration

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            us.registerUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration successful! Please login.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
 // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
     // Handle login
     @PostMapping("/login")
     public ResponseEntity<?> loginUser(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpSession session) {

         User user = us.authenticateUser(username, password);

         if (user != null) {
             session.setAttribute("loggedInUser", user);
             return ResponseEntity.ok().body(Map.of("message", "Login successful!"));
         } else {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                     .body(Map.of("error", "Invalid username or password!"));
         }
     }


    // Handle logout
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Logged out successfully!");
        return "redirect:/auth/login";
    }
	
}
