package com.api.libraryapirest.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase que representa la entidad Book en la base de datos.
 * Utiliza JPA para mapear la tabla 'book' y Lombok para generar
 * automáticamente getters, setters, constructores y el patrón builder.
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntiti {

    /**
     * Identificador único para cada libro.
     * Generado automáticamente mediante la estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Título del libro.
     */
    private String title;

    /**
     * Autor del libro.
     */
    private String author;

    /**
     * Fecha de publicación del libro.
     */
    private LocalDate publisherDate;

    /**
     * Código ISBN del libro, que debe ser único.
     */
    @Column(unique = true)
    private String isbn;

    /**
     * Precio del libro.
     */
    private BigDecimal price;

}
