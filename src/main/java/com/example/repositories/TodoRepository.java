package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Todo;
import com.example.entities.User;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser(User user);

    Optional<Todo> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);

    boolean existsByIdAndUser(Long id, User user);

}

