package com.biblioteca.api.application.dto;

public record BookDTO(
        Long id,
        String title,
        String author,
        String genre,
        String language,
        int number_of_pages,
        int stock
        ) {
}
