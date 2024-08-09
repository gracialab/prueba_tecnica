package com.natalyrodriguez.ApiTest.mapper;

import com.natalyrodriguez.ApiTest.dto.UserDTO;
import com.natalyrodriguez.ApiTest.model.User;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    Optional<User> toEntity(UserDTO userDTO);

    UserDTO toDTO(Optional<User> savedUser);
}
