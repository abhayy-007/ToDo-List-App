package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.User;
import com.example.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user_model", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user_model") User user, Model model) {
        if (user.getUsername() == null || user.getUsername().trim().length() < 3) {
            model.addAttribute("error", "Username must be at least 3 characters");
            return "register";
        }

        if (user.getPassword() == null || user.getPassword().trim().length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters");
            return "register";
        }

        boolean status = userService.createUser(user);

        if (status) {
            model.addAttribute("param_registered", true);
            return "redirect:/login?registered";
        }

        model.addAttribute("error", "Username is already taken. Please choose another.");
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
