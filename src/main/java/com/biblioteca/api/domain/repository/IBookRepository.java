package com.biblioteca.api.domain.repository;

import com.biblioteca.api.domain.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
}
