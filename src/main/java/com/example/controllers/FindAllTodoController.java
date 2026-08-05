package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.entities.Todo;
import com.example.services.TodoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FindAllTodoController {
    
    @Autowired
    private TodoService todoService;

    @GetMapping({"/", "/list_all"})
    public String showAllTodo(Model model) {

        List<Todo> todo_list = todoService.findAllTodo();

        model.addAttribute("todo_list", todo_list);

        return "index";
    }
    

}
