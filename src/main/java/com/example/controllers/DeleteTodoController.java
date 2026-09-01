package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.services.TodoService;

@Controller
public class DeleteTodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable Long id, Authentication authentication,
            RedirectAttributes redirectAttributes) {
        try {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            boolean deleted = todoService.deleteTodoById(id, user);
            if (deleted) {
                redirectAttributes.addFlashAttribute("success_msg", "Task deleted successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error_msg", "Task not found or access denied.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error_msg", "Failed to delete task: " + e.getMessage());
        }

        return "redirect:/";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todos/delete/{id}")
    public String deleteTodoGetRedirect() {
        return "redirect:/";
    }

}
