package com.biblioteca.api.application.service.impl;

import com.biblioteca.api.application.dto.UserDTO;
import com.biblioteca.api.application.service.IUserService;
import com.biblioteca.api.domain.exception.ResourceNotFoundException;
import com.biblioteca.api.domain.model.UserEntity;
import com.biblioteca.api.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userE = iUserRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado con el id: "+id));

        return convertToDTO(userE);
    }

    @Override
    public UserDTO saveUser(UserDTO userSave) {
        UserEntity userE = convertToEntity(userSave);
        userE.setPassword(passwordEncoder.encode(userE.getPassword()));
        UserEntity savedUser = iUserRepository.save(userE);

        return convertToDTO(savedUser);
    }

    // Convierte de UserEntity a UserDTO
    public UserDTO convertToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastname(),
                userEntity.getDocument(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getPhone()
        );
    }

    // Convierte de UserDTO a UserEntity
    public UserEntity convertToEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.id(),
                userDTO.name(),
                userDTO.lastname(),
                userDTO.document(),
                userDTO.username(),
                userDTO.email(),
                userDTO.password(),
                userDTO.phone()
        );
    }


}
