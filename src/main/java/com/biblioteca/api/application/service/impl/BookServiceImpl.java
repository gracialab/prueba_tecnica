package com.biblioteca.api.application.service.impl;

import com.biblioteca.api.application.dto.BookDTO;
import com.biblioteca.api.application.service.IBookService;
import com.biblioteca.api.domain.exception.ResourceNotFoundException;
import com.biblioteca.api.domain.model.BookEntity;
import com.biblioteca.api.domain.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements IBookService {
    private final IBookRepository iBookRepository;


    @Override
    public List<BookDTO> getAllBooks() {
        return iBookRepository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        BookEntity book = iBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con el id: "+id));

        return convertToDTO(book);
    }

    @Override
    public BookDTO saveBook(BookDTO book) {
        BookEntity bookE = convertToEntity(book);
        BookEntity savedBook = iBookRepository.save(bookE);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO book) {
        BookEntity existingBook = iBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con el id: "+id));

        existingBook.setTitle(book.title());
        existingBook.setAuthor(book.author());
        existingBook.setGenre(book.genre());
        existingBook.setLanguage(book.language());
        existingBook.setNumberOfPages(book.number_of_pages());
        existingBook.setStock(book.stock());

        BookEntity updateBook = iBookRepository.save(existingBook);

        return convertToDTO(updateBook);
    }

    @Override
    public void deleteBook(Long id) {
        if(!iBookRepository.existsById(id)){
            throw new ResourceNotFoundException("Libro no encontrado con el id: "+id);
        }
        iBookRepository.deleteById(id);
    }


    // Método para convertir una entidad BookEntity a BookDTO
    public BookDTO convertToDTO(BookEntity bookE){
        return new BookDTO(bookE.getId(),
                bookE.getTitle(),
                bookE.getAuthor(),
                bookE.getGenre(),
                bookE.getLanguage(),
                bookE.getNumberOfPages(),
                bookE.getStock());
    }

    // Método para convertir un BookDTO a BookEntity
    public BookEntity convertToEntity(BookDTO book){
        return new BookEntity(
                book.id(),
                book.title(),
                book.author(),
                book.genre(),
                book.language(),
                book.number_of_pages(),
                book.stock()
        );
    }
}
