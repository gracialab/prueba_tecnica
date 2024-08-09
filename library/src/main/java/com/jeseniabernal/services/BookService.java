package com.jeseniabernal.services;

import com.jeseniabernal.datamodel.BookDTO;
import java.util.List;

public interface BookService {
    /**
     * Guarda un nuevo libro en la base de datos.
     * 
     * @param bookDTO Información del libro a guardar.
     * @return El libro guardado con su ID asignado.
     * @throws RuntimeException Si el libro ya existe en la base de datos.
     */
    BookDTO saveBook(BookDTO bookDTO) throws RuntimeException;

    /**
     * Obtiene un libro por su ID.
     * 
     * @param id ID del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    BookDTO getBookById(Long id) throws RuntimeException;

    /**
     * Obtiene un libro por su ISBN.
     * 
     * @param isbn ISBN del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    BookDTO getBookByIsbn(String isbn) throws RuntimeException;

    /**
     * Obtiene una lista de todos los libros en la base de datos.
     * 
     * @return Lista de libros.
     */
    List<BookDTO> getAllBooks();

    /**
     * Elimina un libro de la base de datos.
     * 
     * @param id ID del libro a eliminar.
     * @throws RuntimeException Si el libro no se encuentra en la base de datos.
     */
    void deleteBook(Long id) throws RuntimeException;
}
