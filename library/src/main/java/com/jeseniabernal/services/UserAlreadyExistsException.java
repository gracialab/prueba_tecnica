package com.jeseniabernal.services;

/**
 * Excepción lanzada cuando se intenta crear un usuario que ya existe.
 */
public class UserAlreadyExistsException extends RuntimeException {
    /**
     * Constructor que toma un mensaje de error como parámetro.
     * 
     * @param message Mensaje de error que se mostrará cuando se lance la excepción.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
