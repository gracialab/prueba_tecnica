package com.example.library_api.controllers;

import com.example.library_api.dto.UserDTO;
import com.example.library_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTO1 = userService.getAuthenticatedUser();
        return ResponseEntity.ok(userDTO1);
    }

    @GetMapping("me")
    public ResponseEntity<UserDTO> getAuthenticatedUser() {
        UserDTO userDTO = userService.getAuthenticatedUser();
        return ResponseEntity.ok(userDTO);
    }
}
