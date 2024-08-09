package com.natalyrodriguez.ApiTest.repository;

import com.natalyrodriguez.ApiTest.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Optional<User>, Long> {
    // Método personalizado para buscar un usuario por nombre de usuario
    Optional<User> findByUsername(String username);
}
