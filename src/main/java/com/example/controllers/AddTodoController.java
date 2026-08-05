package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.entities.TodoPriority;
import com.example.entities.TodoStatus;
import com.example.services.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AddTodoController {

    @Autowired
    private final TodoService todoService;

    public AddTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String addTodoPage(Model model) {

        model.addAttribute("todo_schema", new Todo());
        model.addAttribute("statuses", TodoStatus.values());
        model.addAttribute("priorities", TodoPriority.values());
        return "add-todo";
    }

    @PostMapping("/todos/add")
    public String addTodo(@ModelAttribute("todo_schema") Todo todo) {

        try {
            todoService.saveTodo(todo);
            // model.addAttribute("success_msg", "Todo saved successfully.");
        } catch (Exception e) {
            // model.addAttribute("error_msg", "Failed to save todo.");
        }

        return "redirect:/";
    }

}
