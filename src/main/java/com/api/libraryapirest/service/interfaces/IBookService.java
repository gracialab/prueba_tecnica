package com.api.libraryapirest.service.interfaces;

import com.api.libraryapirest.presentation.dto.BookDTO;

import java.util.List;

public interface IBookService  {
    List<BookDTO> findAll();
    BookDTO findById(long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO, long id);
    String deleteBook(long id);
}
