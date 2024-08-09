package com.tecnica.prueba.DTO;

import com.tecnica.prueba.domain.book.Book;

public record BookListDTO(
        Long id,
        String name
) {
    public BookListDTO(Book book){
        this(
                book.getId(),
                book.getNombre()
        );
    }

}
