package com.example.controllers;

import com.example.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.entities.TodoPriority;
import com.example.entities.TodoStatus;
import com.example.entities.User;
import com.example.services.TodoService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditTodoController {

    private final UserRepository userRepository;
    @Autowired
    private TodoService todoService;

    EditTodoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todos/update/{id}")
    public String editTodoPage(Model model, @PathVariable Long id, Authentication authentication,
            RedirectAttributes redirectAttributes) {

        try {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            Todo todo = todoService.findTodoById(id, user);

            model.addAttribute("update_todo", todo);
            model.addAttribute("statuses", TodoStatus.values());
            model.addAttribute("priorities", TodoPriority.values());

            return "edit-todo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error_msg", "Task not found or access denied.");
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/todos/update/{id}")
    public String editTodo(@Valid @ModelAttribute("update_todo") Todo todo,
            BindingResult bindingResult,
            @PathVariable Long id,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            todo.setId(id);
            model.addAttribute("statuses", TodoStatus.values());
            model.addAttribute("priorities", TodoPriority.values());
            return "edit-todo";
        }

        try {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            todoService.updateTodo(id, todo, user);
            redirectAttributes.addFlashAttribute("success_msg", "Task updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error_msg", "Failed to update task: " + e.getMessage());
        }

        return "redirect:/";
    }

}
