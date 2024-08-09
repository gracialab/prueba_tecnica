package com.tecnica.prueba.DTO;

import com.tecnica.prueba.domain.detbook.DetBook;

public record DetBookDTO(
        String autor,
        String editorial,
        String fechaPublicacion

) {
    public DetBookDTO(DetBook detBook)
    {
        this(
                detBook.getAutor(),
                detBook.getEditorial(),
                detBook.getfecha_publicacion()
        );
    }
}
