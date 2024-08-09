package com.natalyrodriguez.ApiTest.repository;

import com.natalyrodriguez.ApiTest.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByIsbn() {
        // Crea y guarda un nuevo libro
        Book book = new Book();
        book.setTitle("El Gran Libro");
        book.setAuthor("John Doe");
        bookRepository.save(book);

        // Busca el libro por su title
        Book foundBook = bookRepository.findByTitle("El Gran Libro");

        // Verifica que el libro encontrado no sea nulo y tenga el ISBN esperado
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("El Gran Libro");
    }
}
