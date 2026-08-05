package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.entities.TodoPriority;
import com.example.entities.TodoStatus;
import com.example.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EditTodoController {
    
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos/update/{id}")
    public String editTodoPage(Model model, @PathVariable Long id) {
        
        Todo todo = todoService.findTodoById(id);

        model.addAttribute("update_todo", todo);
        model.addAttribute("statuses", TodoStatus.values());
        model.addAttribute("priorities", TodoPriority.values());

        return "edit-todo";
    }
    
    @PostMapping("/todos/update/{id}")
    public String editTodo(@ModelAttribute("update_todo") Todo todo, @PathVariable Long id){

        todoService.updateTodo(id, todo);

        return "redirect:/";
    }

}
