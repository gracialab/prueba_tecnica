package com.example.pruebaTecnicaGlab.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.pruebaTecnicaGlab.dto.BookDTO;
import com.example.pruebaTecnicaGlab.dto.BookResponseDTO;
import com.example.pruebaTecnicaGlab.entity.Book;
import com.example.pruebaTecnicaGlab.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(this::convertToBookResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return convertToBookResponseDTO(bookService.getBookById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO saveBook(@RequestBody BookDTO bookDTO) {
        Book book = convertToBookEntity(bookDTO);
        Book savedBook = bookService.saveBook(book);
        return convertToBookResponseDTO(savedBook);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBook = Optional.ofNullable(bookService.getBookById(id));
        if (existingBook.isPresent()) {
            Book update = existingBook.get();
            update.setTitulo(bookDTO.getTitulo());
            update.setAutor(bookDTO.getAutor());
            update.setGenero(bookDTO.getGenero());
            // Update other fields as needed

            return convertToBookResponseDTO(bookService.saveBook(update));
        }
        return null; // or handle the case when the book is not found
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Convert Book entity to BookResponseDTO
    private BookResponseDTO convertToBookResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitulo(book.getTitulo());
        dto.setAutor(book.getAutor());
        dto.setGenero(book.getGenero());
        return dto;
    }

    // Convert BookDTO to Book entity
    private Book convertToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitulo(bookDTO.getTitulo());
        book.setAutor(bookDTO.getAutor());
        book.setGenero(bookDTO.getGenero());
        return book;
    }
}









/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.pruebaTecnicaGlab.entity.Book;
import com.example.pruebaTecnicaGlab.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> getBookById(@PathVariable Long id) {
        return Optional.ofNullable(bookService.getBookById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existingBook = Optional.ofNullable(bookService.getBookById(id));
        if (existingBook.isPresent()) {
            Book update = existingBook.get();
            update.setTitulo(book.getTitulo());
            update.setAutor(book.getAutor());
            update.setGenero(book.getGenero());
            // Update other fields as needed

            return Optional.of(bookService.saveBook(update));
        }
        return Optional.of(book); // or Optional.empty() based on your preference
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
 */
