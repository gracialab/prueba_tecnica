package com.biblioteca.api.controller;

import com.biblioteca.api.application.security.jwt.DataJWTToken;
import com.biblioteca.api.application.dto.UserDTO;
import com.biblioteca.api.application.security.jwt.TokenService;
import com.biblioteca.api.application.service.IUserService;
import com.biblioteca.api.domain.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/biblioteca")
public class UserController {

    private final IUserService iUserService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DataJWTToken> authUser(@RequestBody UserDTO userSave) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userSave.username(), userSave.password()
        );
        var userAuth = authenticationManager.authenticate(authToken);
        System.out.println(authToken);
        var JWTtoken = tokenService.generateToken((UserEntity) userAuth.getPrincipal());

        return ResponseEntity.ok(new DataJWTToken(JWTtoken));
    }

    @PostMapping("/registrousuario")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userSave) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.saveUser(userSave));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.getUserById(id));
    }
}
