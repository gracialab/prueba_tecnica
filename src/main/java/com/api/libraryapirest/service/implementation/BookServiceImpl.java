package com.api.libraryapirest.service.implementation;

import com.api.libraryapirest.persistence.entities.BookEntiti;
import com.api.libraryapirest.persistence.repository.IBookRepository;
import com.api.libraryapirest.presentation.dto.BookDTO;
import com.api.libraryapirest.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    // Convertir entidad a DTO
    private BookDTO convertToDTO(BookEntiti book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(), // Ten cuidado con la contraseña
                book.getPublisherDate(),
                book.getIsbn(),
                book.getPrice()
        );
    }

    // Convertir DTO a entidad
    private BookEntiti convertToEntity(BookDTO bookDTO) {
        return new BookEntiti(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(), // Ten cuidado con la contraseña
                bookDTO.getPublisherDate(),
                bookDTO.getIsbn(),
                bookDTO.getPrice()
        );
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findById(long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntiti book = convertToEntity(bookDTO);
        book = bookRepository.save(book);
        return convertToDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDTO.getTitle());
                    book.setAuthor(bookDTO.getAuthor());
                    book.setPublisherDate(bookDTO.getPublisherDate());
                    book.setIsbn(bookDTO.getIsbn());
                    book.setPrice(bookDTO.getPrice());
                    return convertToDTO(book);
                }).orElse(null);
    }

    @Override
    public String deleteBook(long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return "Book deleted successfully";
                }).orElse("Book not found");
    }

}
