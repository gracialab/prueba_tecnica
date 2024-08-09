package com.natalyrodriguez.ApiTest.repository;

import com.natalyrodriguez.ApiTest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Método personalizado para buscar un libro por id
    Book findByTitle(String title);
}
