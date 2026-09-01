package com.example.services;

import java.util.List;

import com.example.entities.Todo;
import com.example.entities.User;

public interface TodoService {
    public boolean saveTodo(Todo todo, User user);

    public Todo updateTodo(Long id, Todo todo, User user);

    public List<Todo> findAllTodo();

    public Todo findTodoById(Long id, User user);

    public boolean deleteTodoById(Long id, User user);

    public List<Todo> findAllTodoByUser(User user);
}
