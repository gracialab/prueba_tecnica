package com.example.pruebaTecnicaGlab.service;

import com.example.pruebaTecnicaGlab.IServicio.IServicioBook;
import com.example.pruebaTecnicaGlab.entity.Book;
import com.example.pruebaTecnicaGlab.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IServicioBook {

    @Autowired
    private BookRepository repository;



    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
