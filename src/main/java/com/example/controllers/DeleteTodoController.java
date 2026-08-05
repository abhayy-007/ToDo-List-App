package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeleteTodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos/delete/{id}")
    public String getMethodName(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }
    
}
