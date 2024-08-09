package com.api.libraryapirest.service.interfaces;

import com.api.libraryapirest.presentation.dto.UserDTO;

import java.util.List;

public interface IUserService  {
    List<UserDTO> findAll();
    UserDTO findById(long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, long id);
    String deleteUser(long id);
}
