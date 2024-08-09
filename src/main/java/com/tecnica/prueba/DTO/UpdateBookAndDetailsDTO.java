package com.tecnica.prueba.DTO;

import com.tecnica.prueba.domain.book.Book;

public record UpdateBookAndDetailsDTO(
         Long Id,
         String name,
         DetBookDTO detalle_libro
) {
    public UpdateBookAndDetailsDTO(Book book)
    {
        this(
                book.getId(),
                book.getNombre(),
                book.getDetalle_libro() != null
                        ? new DetBookDTO(
                        book.getDetalle_libro().getAutor(),
                        book.getDetalle_libro().getEditorial(),
                        book.getDetalle_libro().getfecha_publicacion()
                )
                        : null
        );
    }


}
