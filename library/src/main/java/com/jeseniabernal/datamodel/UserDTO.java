package com.jeseniabernal.datamodel;

/**
 * Representa un objeto de transferencia de datos (DTO) para un usuario.
 */
public class UserDTO {

    /**
     * El identificador único del usuario.
     */
    private Long id;

    /**
     * El nombre de usuario.
     */
    private String username;

    /**
     * La contraseña del usuario.
     */
    private String password;

    /**
     * Constructor sin argumentos.
     */
    public UserDTO() {}

    /**
     * Constructor con argumentos.
     *
     * @param id       El identificador único del usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     */
    public UserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

        // Validaciones adicionales
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username El nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;

        // Validaciones adicionales
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío.");
        }
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;

        // Validaciones adicionales
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
    }
}
