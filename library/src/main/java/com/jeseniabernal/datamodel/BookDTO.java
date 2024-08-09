package com.jeseniabernal.datamodel;

/**
 * Representa un objeto de transferencia de datos (DTO) para un libro.
 */
public class BookDTO {

    /**
     * El identificador único del libro.
     */
    private Long id;

    /**
     * El título del libro.
     */
    private String title;

    /**
     * El autor del libro.
     */
    private String author;

    /**
     * El número de ISBN del libro.
     */
    private String isbn;

    /**
     * El año de publicación del libro.
     */
    private Integer publishedYear;

    /**
     * Constructor sin argumentos.
     */
    public BookDTO() {}

    /**
     * Constructor con argumentos.
     *
     * @param id           El identificador único del libro.
     * @param title        El título del libro.
     * @param author       El autor del libro.
     * @param isbn         El número de ISBN del libro.
     * @param publishedYear El año de publicación del libro.
     */
    public BookDTO(Long id, String title, String author, String isbn, Integer publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;

        // Validaciones adicionales
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
        }
    }

    /**
     * Obtiene el identificador único del libro.
     *
     * @return El identificador único del libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del libro.
     *
     * @param id El identificador único del libro.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del libro.
     *
     * @param title El título del libro.
     */
    public void setTitle(String title) {
        this.title = title;

        // Validaciones adicionales
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Establece el autor del libro.
     *
     * @param author El autor del libro.
     */
    public void setAuthor(String author) {
        this.author = author;

        // Validaciones adicionales
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
        }
    }

    /**
     * Obtiene el número de ISBN del libro.
     *
     * @return El número de ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el número de ISBN del libro.
     *
     * @param isbn El número de ISBN del libro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el año de publicación del libro.
     *
     * @return El año de publicación del libro.
     */
    public Integer getPublishedYear() {
        return publishedYear;
    }

    /**
     * Establece el año de publicación del libro.
     *
     * @param publishedYear El año de publicación del libro.
     */
    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
