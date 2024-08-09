package com.api.libraryapirest.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa la entidad User en la base de datos.
 * Utiliza JPA para mapear la tabla 'user' y Lombok para generar
 * automáticamente getters, setters, constructores y el patrón builder.
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntiti {

    /**
     * Identificador único para cada usuario.
     * Generado automáticamente mediante la estrategia IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre de usuario del usuario, que debe ser único y no puede ser nulo.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Contraseña del usuario, que no puede ser nula.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Dirección de correo electrónico del usuario, que debe ser única.
     */
    @Column(unique = true)
    private String email;

    /**
     * Número de teléfono del usuario, que no puede ser nulo.
     */
    @Column(nullable = false)
    private String phoneNumber;

    /**
     * Dirección del usuario.
     */
    private String address;

    /**
     * Nombre de pila del usuario, que no puede ser nulo.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Apellido del usuario, que no puede ser nulo.
     */
    @Column(nullable = false)
    private String lastName;

}
