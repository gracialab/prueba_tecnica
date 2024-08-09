package com.api.libraryapirest.persistence.repository;

import com.api.libraryapirest.persistence.entities.UserEntiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de la entidad User.
 * Extiende JpaRepository para proporcionar métodos CRUD básicos y consultas adicionales.
 *
 * @see JpaRepository
 * @see UserEntiti
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntiti, Long> {
}
