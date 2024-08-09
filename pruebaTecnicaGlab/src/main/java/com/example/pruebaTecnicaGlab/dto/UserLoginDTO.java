package com.example.pruebaTecnicaGlab.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Data
public class UserLoginDTO {

    @NotEmpty(message = "Username es requerido")
    private String username;

    @NotEmpty(message = "Password es requerido")
    private String password;
}
