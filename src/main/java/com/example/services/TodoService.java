package com.example.services;

import java.util.List;

import com.example.entities.Todo;

public interface TodoService {
    public boolean saveTodo(Todo todo);
    public Todo updateTodo(Long id, Todo todo);
    public List<Todo> findAllTodo();
    public Todo findTodoById( Long id);
    public boolean deleteTodoById(Long id);
}
