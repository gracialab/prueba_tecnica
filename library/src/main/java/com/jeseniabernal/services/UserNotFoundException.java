package com.jeseniabernal.services;

/**
 * Excepción lanzada cuando se intenta buscar o eliminar un usuario que no existe.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructor que toma un mensaje de error como parámetro.
     * 
     * @param message Mensaje de error que se mostrará cuando se lance la excepción.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
