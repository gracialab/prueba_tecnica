package com.jeseniabernal.services;

import com.jeseniabernal.datamodel.UserDTO;
import java.util.List;

/**
 * La interfaz UserService proporciona métodos para interactuar con la entidad User.
 * Esta interfaz es responsable de encapsular la lógica de negocio relacionada con los usuarios.
 */
public interface UserService {
    /**
     * Crea un nuevo usuario o actualiza uno existente. Si el usuario ya existe, lanza una excepción UserAlreadyExistsException.
     * 
     * @param userDTO el objeto UserDTO que se va a guardar
     * @return el objeto UserDTO guardado
     * @throws UserAlreadyExistsException si el usuario ya existe
     */
    UserDTO saveUser(UserDTO userDTO) throws UserAlreadyExistsException;

    /**
     * Busca un usuario por su ID y devuelve un objeto UserDTO si se encuentra. Si no se encuentra, devuelve null.
     * 
     * @param id el ID del usuario que se va a buscar
     * @return el objeto UserDTO encontrado
     */
    UserDTO getUserById(Long id);

    /**
     * Busca un usuario por su nombre de usuario y devuelve un objeto UserDTO si se encuentra. Si no se encuentra, devuelve null.
     * 
     * @param username el nombre de usuario que se va a buscar
     * @return el objeto UserDTO encontrado
     */
    UserDTO getUserByUsername(String username);

    /**
     * Devuelve una lista de todos los usuarios registrados.
     * 
     * @return la lista de usuarios
     */
    List<UserDTO> getAllUsers();

    /**
     * Elimina un usuario por su ID. Si el usuario no existe, lanza una excepción UserNotFoundException.
     * 
     * @param id el ID del usuario que se va a eliminar
     * @throws UserNotFoundException si el usuario no existe
     */
    void deleteUser(Long id) throws UserNotFoundException;
}
