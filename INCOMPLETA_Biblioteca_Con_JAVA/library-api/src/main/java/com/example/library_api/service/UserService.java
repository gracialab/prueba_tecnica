package com.example.library_api.service;

import com.example.library_api.dto.UserDTO;
import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Este servicio se encargará de la lógica relacionada con los usuarios,
 * como el registro y la búsqueda de usuarios.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        // Logica para registrar un nuevo usuario
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);

        
        return new UserDTO(savedUser.getId(), savedUser.getUsername());
    }

    public UserDTO getAuthenticatedUser() {
        return null;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

}
