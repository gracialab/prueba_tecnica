package com.tecnica.prueba.controller;

import com.tecnica.prueba.DTO.AuthDTO;
import com.tecnica.prueba.DTO.RegisterDTO;
import com.tecnica.prueba.domain.user.User;
import com.tecnica.prueba.infra.security.DataJWTToken;
import com.tecnica.prueba.infra.security.TokenService;
import com.tecnica.prueba.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final AuthenticationManager authenticationManager;

    final TokenService tokenService;

    final IUserRepository iUserRepository;

    final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, IUserRepository iUserRepository, PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
}

    @PostMapping("/login")
    public ResponseEntity<DataJWTToken> authUser(@RequestBody @Valid AuthDTO authDTO) {
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(authDTO.name(), authDTO.password());
            Authentication authenticated = authenticationManager.authenticate(authToken);
            String JWTtoken = tokenService.GenerateToken((User) authenticated.getPrincipal());
            return ResponseEntity.ok(new DataJWTToken(JWTtoken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DataJWTToken("Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO registerDTO, UriComponentsBuilder uriComponentsBuilder) {
        Optional<User> existingUser = iUserRepository.findByEmail(registerDTO.email());
        if (existingUser.isPresent()) {
            String errorMessage = "Este correo ya está registrado";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        User newUser = new User();
        newUser.setName(registerDTO.name());
        newUser.setLastname(registerDTO.lastname());
        newUser.setEmail(registerDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerDTO.password())); // Encriptar la contraseña

        iUserRepository.save(newUser);

        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(url).body(newUser);
    }

    }

