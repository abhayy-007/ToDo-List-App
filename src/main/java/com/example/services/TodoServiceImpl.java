package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Todo;
import com.example.repositories.TodoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional
    public boolean saveTodo(Todo todo) {
        todoRepository.save(todo);
        return true;
    }

    @Override
    @Transactional
    public Todo updateTodo(Long id, Todo todo) {

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

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
    public Todo findTodoById(Long id) {
        Optional <Todo> optional = todoRepository.findById(id);
        Todo todo = optional.get();

        return todo;
    }

    @Override
    public boolean deleteTodoById(Long id) {
        Optional<Todo> optional = todoRepository.findById(id);
        Todo todo = optional.get();

        if (todo != null) {
            todoRepository.delete(todo);
            return true;
        }

        return false;
    }

}
