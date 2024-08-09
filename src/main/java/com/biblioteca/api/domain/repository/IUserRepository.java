package com.biblioteca.api.domain.repository;

import com.biblioteca.api.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);
}
