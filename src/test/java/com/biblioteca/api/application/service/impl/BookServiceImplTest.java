package com.biblioteca.api.application.service.impl;

import com.biblioteca.api.application.dto.BookDTO;
import com.biblioteca.api.domain.model.BookEntity;
import com.biblioteca.api.domain.repository.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookServiceImpl bookService;
    @MockBean
    private IBookRepository bookRepository;

    @BeforeEach
    void setUp() {
        BookEntity book = new BookEntity();
        book.setId(12L);
        book.setTitle("Titulo Libro");
        book.setAuthor("Jose");
        book.setGenre("Ficción");
        book.setLanguage("Español");
        book.setNumberOfPages(300);
        book.setStock(10);

        Mockito.when(bookRepository.findById(12L)).thenReturn(Optional.of(book));
    }
    @Test
    @DisplayName("Debe traer el libro si su id existe")
    void getBookById(){
        Long idBook = 12L;
        BookDTO book = bookService.getBookById(idBook);
        assertEquals(idBook, book.id());
        System.out.println(book);
    }
}