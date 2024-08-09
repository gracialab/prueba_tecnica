package com.api.libraryapirest.persistence.repository;

import com.api.libraryapirest.persistence.entities.BookEntiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de la entidad Book.
 * Extiende JpaRepository para proporcionar métodos CRUD básicos y consultas adicionales.
 *
 * @see JpaRepository
 * @see BookEntiti
 */
@Repository
public interface IBookRepository extends JpaRepository<BookEntiti, Long> {
}
