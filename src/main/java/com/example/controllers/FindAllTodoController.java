package com.example.controllers;

import com.example.repositories.UserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.entities.User;
import com.example.services.TodoService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindAllTodoController {

    private final UserRepository userRepository;
    @Autowired
    private TodoService todoService;

    FindAllTodoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({ "/", "/list_all" })
    public String showAllTodo(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        User user = userRepository.findByUsername(authentication.getName()).orElse(null);
        if (user == null) {
            return "redirect:/login?logout";
        }

        List<Todo> todo_list = todoService.findAllTodoByUser(user);
        model.addAttribute("todo_list", todo_list);

        return "index";
    }

}
