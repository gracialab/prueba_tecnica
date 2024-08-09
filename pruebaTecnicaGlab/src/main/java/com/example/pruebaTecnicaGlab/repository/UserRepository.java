package com.example.pruebaTecnicaGlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebaTecnicaGlab.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}
