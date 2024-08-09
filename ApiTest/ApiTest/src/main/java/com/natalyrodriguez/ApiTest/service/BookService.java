package com.natalyrodriguez.ApiTest.service;

import com.natalyrodriguez.ApiTest.dto.BookDTO;
import com.natalyrodriguez.ApiTest.mapper.BookMapper;
import com.natalyrodriguez.ApiTest.model.Book;
import com.natalyrodriguez.ApiTest.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Inyectar el BookRepository a través del constructor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Método para crear un nuevo libro
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.toEntity(bookDTO); // Convierte el DTO a la entidad
        Book savedBook = bookRepository.save(book); // Guarda el libro en la base de datos
        return BookMapper.INSTANCE.toDTO(savedBook); // Convierte la entidad guardada a DTO
    }

    // Método para obtener un libro por su ID
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.INSTANCE.toDTO(book);
    }

    // Método para obtener todos los libros
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    // Método para actualizar un libro existente
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setId(bookDTO.getId());
        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.INSTANCE.toDTO(updatedBook);
    }

    // Método para eliminar un libro
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
