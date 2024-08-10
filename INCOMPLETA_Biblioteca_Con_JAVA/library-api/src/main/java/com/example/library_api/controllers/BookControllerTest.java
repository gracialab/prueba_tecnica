package com.example.library_api.controllers;

import com.example.library_api.dto.BookDTO;
import com.example.library_api.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        BookDTO bookDTO = new BookDTO(null, "Test Title", "Test Author", "1234567890");
        BookDTO savedBookDTO = new BookDTO(1L, "Test Title", "Test Author", "1234567890");

        when(bookService.createBook(bookDTO)).thenReturn(savedBookDTO);

        ResponseEntity<BookDTO> response = bookController.createBook(bookDTO);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(savedBookDTO, response.getBody());
        verify(bookService, times(1)).createBook(bookDTO);
    }

    @Test
    void testGetBooks() {
        List<BookDTO> books = Arrays.asList(
                new BookDTO(1L, "Title1", "Author1", "1234567890"),
                new BookDTO(2L, "Title2", "Author2", "0987654321")
        );

        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getBooks();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getBooks();
    }

    @Test
    void testGetBookById() {
        BookDTO bookDTO = new BookDTO(1L, "Test Title", "Test Author", "1234567890");

        when(bookService.getBookById(1L)).thenReturn(Optional.of(bookDTO));

        ResponseEntity<BookDTO> response = bookController.getBookById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookDTO, response.getBody());
        verify(bookService, times(1)).getBookById(1L);
    }
}
