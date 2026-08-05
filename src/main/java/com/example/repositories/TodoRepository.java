package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
