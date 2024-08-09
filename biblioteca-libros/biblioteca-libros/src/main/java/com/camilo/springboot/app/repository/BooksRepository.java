package com.camilo.springboot.app.repository;

import com.camilo.springboot.app.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BooksRepository extends JpaRepository<Books, Long> {
}
