package com.jeseniabernal.repositories;

import com.jeseniabernal.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Book.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Busca un libro por su ISBN.
     * 
     * @param isbn el ISBN del libro a buscar
     * @return el libro encontrado, o null si no existe
     */
    Book findByIsbn(String isbn);
}
