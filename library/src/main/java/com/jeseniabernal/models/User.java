/**
 * Clase que representa un usuario en el sistema.
 * 
 * @author Jesenia Bernal
 * @version 1.0
 */
package com.jeseniabernal.models;

import jakarta.persistence.*;

/**
 * Entidad que se puede persistir en una base de datos.
 */
@Entity
public class User {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Nombre de usuario.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Contraseña del usuario.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Constructor vacío para la creación de objetos User.
     */
    public User() {}

    /**
     * Constructor que inicializa un objeto User con un nombre de usuario y una contraseña.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @throws IllegalArgumentException si el nombre de usuario o la contraseña son nulos o vacíos.
     */
    public User(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo ni vacío");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula ni vacía");
        }
        this.username = username;
        this.password = password;
    }

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return Identificador único del usuario.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id Identificador único del usuario.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username Nombre de usuario.
     * @throws IllegalArgumentException si el nombre de usuario es nulo o vacío.
     */
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo ni vacío");
        }
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Contraseña del usuario.
     * @throws IllegalArgumentException si la contraseña es nula o vacía.
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula ni vacía");
        }
        this.password = password;
    }
}
