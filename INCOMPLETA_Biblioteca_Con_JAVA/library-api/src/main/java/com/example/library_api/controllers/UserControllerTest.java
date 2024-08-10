package com.example.library_api.controllers;

import com.example.library_api.dto.UserDTO;
import com.example.library_api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        UserDTO userDTO = new UserDTO(null, "testuser");
        UserDTO savedUserDTO = new UserDTO(1L, "testuser");

        when(userService.registerUser(userDTO)).thenReturn(savedUserDTO);

        ResponseEntity<UserDTO> response = userController.registerUser(userDTO);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(savedUserDTO, response.getBody());
        verify(userService, times(1)).registerUser(userDTO);
    }

    @Test
    void testGetAuthenticatedUser() {
        UserDTO userDTO = new UserDTO(1L, "testuser");

        when(userService.getAuthenticatedUser()).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getAuthenticatedUser();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getAuthenticatedUser();
    }
}
