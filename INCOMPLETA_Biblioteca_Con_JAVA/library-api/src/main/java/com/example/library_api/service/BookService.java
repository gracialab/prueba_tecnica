package com.example.library_api.service;


import com.example.library_api.dto.BookDTO;
import com.example.library_api.model.Book;
import com.example.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *Este servicio manejará la lógica de negocio relacionada con los libros,
 * incluyendo la creación, actualización y eliminación de libros.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());

        Book savedBook = bookRepository.save(book);

        return convertToDTO(savedBook);


    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToDTO)
                   .orElse(null);
    }



    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setIsbn(bookDTO.getIsbn());
            Book updatedBook = bookRepository.save(book);
            return convertToDTO(updatedBook);
        } else {
            return null; // O manejar la ausencia del libro según tus necesidades
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Método para convertir Book a BookDTO
    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn());
    }

}
