package com.biblioteca.api.application.service;

import com.biblioteca.api.application.dto.UserDTO;


public interface IUserService {

    public UserDTO getUserById(Long id);

    public UserDTO saveUser(UserDTO userSave);

}
