package com.camilo.springboot.app.service;
import com.camilo.springboot.app.entity.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    public abstract Books insertaActulizaBooks(Books books);

    public abstract List<Books> listaBooks();

    public abstract Optional<Books> findBooksById(Long id);

    public abstract void deleteBooksById(Long id);
}
