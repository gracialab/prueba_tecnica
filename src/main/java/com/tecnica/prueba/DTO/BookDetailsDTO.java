package com.tecnica.prueba.DTO;

import com.tecnica.prueba.domain.detbook.DetBook;

public record BookDetailsDTO(
        Long id,
        String autor,
        String editorial,
        String fechaPublicacion
) {
    public BookDetailsDTO(DetBook detBook) {
        this(
                detBook.getId(),
                detBook.getAutor(),
                detBook.getEditorial(),
                detBook.getfecha_publicacion()
                );
    }
}
