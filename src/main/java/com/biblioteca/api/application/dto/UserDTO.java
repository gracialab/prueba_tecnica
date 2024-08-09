package com.biblioteca.api.application.dto;

public record UserDTO(
        Long id,
        String name,
        String lastname,
        String document,
        String username,
        String email,
        String password,
        String phone

) {
}
