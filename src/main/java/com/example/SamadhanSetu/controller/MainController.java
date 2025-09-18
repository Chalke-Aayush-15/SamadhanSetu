package com.example.SamadhanSetu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SamadhanSetu.entity.User;
import com.example.SamadhanSetu.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class MainController {

	@Autowired
	private UserService us;
	
	// Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    // Handle registration
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                              BindingResult bindingResult, 
                              Model model, 
                              RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        try {
            us.registerUser(user);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/auth/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    
 // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
 // Handle login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, 
                           @RequestParam String password, 
                           HttpSession session, 
                           Model model, 
                           RedirectAttributes redirectAttributes) {
        
        User user = us.authenticateUser(username, password);
        
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            redirectAttributes.addFlashAttribute("success", "Login successful!");
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
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
