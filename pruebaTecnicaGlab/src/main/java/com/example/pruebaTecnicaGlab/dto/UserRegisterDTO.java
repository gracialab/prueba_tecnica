package com.example.pruebaTecnicaGlab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegisterDTO {

    @NotEmpty(message = "Username es requerido")
    private String username;

    @NotEmpty(message = "Password es requerido")
    private String password;

    @NotEmpty(message = "Email es requerido")
    @Email(message = "Email debe ser válido")
    private String email;
}

