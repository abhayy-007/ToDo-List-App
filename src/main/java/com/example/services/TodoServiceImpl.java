package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Todo;
import com.example.entities.User;
import com.example.repositories.TodoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    @Transactional
    public boolean saveTodo(Todo todo, User user) {
        if (todo == null || user == null) {
            return false;
        }
        todo.setUser(user);
        todoRepository.save(todo);
        return true;
    }

    @Override
    @Transactional
    public Todo updateTodo(Long id, Todo todo, User user) {
        Todo existingTodo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo not found or not authorized with id: " + id));

        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }

        if (todo.getDescription() != null) {
            existingTodo.setDescription(todo.getDescription());
        }

        if (todo.getDueDate() != null) {
            existingTodo.setDueDate(todo.getDueDate());
        }

        if (todo.getStatus() != null) {
            existingTodo.setStatus(todo.getStatus());
        }

        if (todo.getPriority() != null) {
            existingTodo.setPriority(todo.getPriority());
        }

        return todoRepository.save(existingTodo);
    }

    @Override
    public List<Todo> findAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findTodoById(Long id, User user) {
        return todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo not found or not authorized with id: " + id));
    }

    @Override
    @Transactional
    public boolean deleteTodoById(Long id, User user) {
        Optional<Todo> optional = todoRepository.findByIdAndUser(id, user);
        if (optional.isPresent()) {
            todoRepository.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Todo> findAllTodoByUser(User user) {
        return todoRepository.findByUser(user);
    }

}
