package com.camilo.springboot.app.service;

import com.camilo.springboot.app.entity.Books;
import com.camilo.springboot.app.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImplement implements BooksService {

    @Autowired
    private BooksRepository repository;

    @Override
    public Books insertaActulizaBooks(Books books) {
        return repository.save(books);
    }

    @Override
    public List<Books> listaBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<Books> findBooksById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteBooksById(Long id) {
        repository.deleteById(id);
    }
}
