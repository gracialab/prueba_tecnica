package com.natalyrodriguez.ApiTest.service;

import com.natalyrodriguez.ApiTest.dto.UserDTO;
import com.natalyrodriguez.ApiTest.mapper.UserMapper;
import com.natalyrodriguez.ApiTest.model.User;
import com.natalyrodriguez.ApiTest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Inyectar el UserRepository a través del constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para registrar un nuevo usuario
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<User> user = UserMapper.INSTANCE.toEntity(userDTO); // Convierte el DTO a la entidad
        Optional<User> savedUser = userRepository.save(user); // Guarda el usuario en la base de datos
        return UserMapper.INSTANCE.toDTO(savedUser); // Convierte la entidad guardada a DTO
    }

    // Método para obtener un usuario por su nombre de usuario
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return UserMapper.INSTANCE.toDTO(user);
    }

    // Método para obtener el perfil de usuario
    public UserDTO getUserProfile(Long id) {
        Optional<User> user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.toDTO(user);
    }

    // Método para obtener todos los usuarios
    public List<UserDTO> getAllUsers() {
        List<Optional<User>> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
