package com.jeseniabernal.services;

import com.jeseniabernal.datamodel.BookDTO;
import com.jeseniabernal.models.Book;
import com.jeseniabernal.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz BookService que interactúa con la base de datos.
 */
@Service
public class BookServiceImpl implements BookService {
    /**
     * Repositorio de libros que se utiliza para acceder a la base de datos.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Conversor de modelos que se utiliza para convertir entre Book y BookDTO.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda un nuevo libro en la base de datos.
     * 
     * @param bookDTO Información del libro a guardar.
     * @return El libro guardado con su ID asignado.
     * @throws RuntimeException Si el libro ya existe en la base de datos.
     */
    @Override
    public BookDTO saveBook(BookDTO bookDTO) throws RuntimeException {
        Book book = modelMapper.map(bookDTO, Book.class);
        if (bookRepository.findByIsbn(book.getIsbn()) != null) {
            throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    /**
     * Obtiene un libro por su ID.
     * 
     * @param id ID del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    @Override
    public BookDTO getBookById(Long id) throws RuntimeException {
        return bookRepository.findById(id).map(book -> modelMapper.map(book, BookDTO.class))
            .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found"));
    }

    /**
     * Obtiene un libro por su ISBN.
     * 
     * @param isbn ISBN del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    @Override
    public BookDTO getBookByIsbn(String isbn) throws RuntimeException {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new RuntimeException("Book with ISBN " + isbn + " not found");
        }
        return modelMapper.map(book, BookDTO.class);
    }

    /**
     * Obtiene una lista de todos los libros en la base de datos.
     * 
     * @return Lista de libros.
     */
    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
            .map(book -> modelMapper.map(book, BookDTO.class))
            .collect(Collectors.toList());
    }

    /**
     * Elimina un libro de la base de datos.
     * 
     * @param id ID del libro a eliminar.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    @Override
    public void deleteBook(Long id) throws RuntimeException {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }
}
