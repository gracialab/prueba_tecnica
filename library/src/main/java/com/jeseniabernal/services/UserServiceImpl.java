package com.jeseniabernal.services;

import com.jeseniabernal.datamodel.UserDTO;
import com.jeseniabernal.models.User;
import com.jeseniabernal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de usuarios.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Repositorio de usuarios.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Conversor de modelos.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda un nuevo usuario.
     *
     * @param userDTO datos del usuario a guardar
     * @return usuario guardado
     * @throws UserAlreadyExistsException si el usuario ya existe
     */
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    /**
     * Obtiene un usuario por ID.
     *
     * @param id ID del usuario
     * @return usuario encontrado
     * @throws UserNotFoundException si el usuario no existe
     */
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("El usuario no existe");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Obtiene un usuario por nombre de usuario.
     *
     * @param username nombre de usuario
     * @return usuario encontrado
     * @throws UserNotFoundException si el usuario no existe
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("El usuario no existe");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Obtiene la lista de todos los usuarios.
     *
     * @return lista de usuarios
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un usuario.
     *
     * @param id ID del usuario
     * @throws UserNotFoundException si el usuario no existe
     */
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("El usuario no existe");
        }
        userRepository.deleteById(id);
    }

    /**
     * Excepción lanzada cuando se intenta guardar un usuario que ya existe.
     */
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    /**
     * Excepción lanzada cuando se intenta obtener un usuario que no existe.
     */
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
