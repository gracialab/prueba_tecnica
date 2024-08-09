package com.biblioteca.api.application.service;

import com.biblioteca.api.application.dto.BookDTO;

import java.util.List;

public interface IBookService {
    public List<BookDTO> getAllBooks();

    public BookDTO getBookById(Long id);

    public BookDTO saveBook(BookDTO book);

    public BookDTO updateBook(Long id, BookDTO book);

    public void deleteBook(Long id);
}
