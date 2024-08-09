package com.api.libraryapirest.service.implementation;

import com.api.libraryapirest.persistence.entities.UserEntiti;
import com.api.libraryapirest.persistence.repository.IUserRepository;
import com.api.libraryapirest.presentation.dto.UserDTO;
import com.api.libraryapirest.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    // Convertir entidad a DTO
    private UserDTO convertToDTO(UserEntiti user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    // Convertir DTO a entidad
    private UserEntiti convertToEntity(UserDTO userDTO) {
        return new UserEntiti(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword(), // Ten cuidado con la contraseña
                userDTO.getEmail(),
                userDTO.getPhoneNumber(),
                userDTO.getAddress(),
                userDTO.getFirstName(),
                userDTO.getLastName()
        );
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntiti user = convertToEntity(userDTO);
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDTO.getUsername());
                    user.setPassword(userDTO.getPassword()); // Ten cuidado con la contraseña
                    user.setEmail(userDTO.getEmail());
                    user.setPhoneNumber(userDTO.getPhoneNumber());
                    user.setAddress(userDTO.getAddress());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user = userRepository.save(user);
                    return convertToDTO(user);
                }).orElse(null);
    }

    @Override
    public String deleteUser(long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return "User deleted successfully";
                }).orElse("User not found");
    }
}
