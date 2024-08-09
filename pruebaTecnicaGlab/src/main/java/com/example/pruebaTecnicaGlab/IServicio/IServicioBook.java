package com.example.pruebaTecnicaGlab.IServicio;

import java.util.List;

import com.example.pruebaTecnicaGlab.entity.Book;

public interface IServicioBook {
    

    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book getBookById(Long id);
    
    void deleteBook(Long id);


}
