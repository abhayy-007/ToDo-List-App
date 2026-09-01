package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.entities.TodoPriority;
import com.example.entities.TodoStatus;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.services.TodoService;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AddTodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todos")
    public String addTodoPage(Model model) {
        if (!model.containsAttribute("todo_schema")) {
            model.addAttribute("todo_schema", new Todo());
        }
        model.addAttribute("statuses", TodoStatus.values());
        model.addAttribute("priorities", TodoPriority.values());
        return "add-todo";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/todos/add")
    public String addTodo(@Valid @ModelAttribute("todo_schema") Todo todo,
            BindingResult bindingResult,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TodoStatus.values());
            model.addAttribute("priorities", TodoPriority.values());
            return "add-todo";
        }

        try {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
            todoService.saveTodo(todo, user);
            redirectAttributes.addFlashAttribute("success_msg", "Task added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error_msg", "Failed to add task: " + e.getMessage());
        }

        return "redirect:/";
    }

}
