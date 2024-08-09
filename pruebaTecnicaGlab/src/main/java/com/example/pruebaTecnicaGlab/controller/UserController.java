package com.example.pruebaTecnicaGlab.controller;

import com.example.pruebaTecnicaGlab.dto.UserRegisterDTO;
import com.example.pruebaTecnicaGlab.entity.User;
import com.example.pruebaTecnicaGlab.dto.UserLoginDTO;
import com.example.pruebaTecnicaGlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {




    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor manual para inyectar las dependencias
    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Usuario con este username ya existe."));
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "Usuario registrado satisfactoriamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginDTO userLoginDTO) {
        User foundUser = userRepository.findByUsername(userLoginDTO.getUsername());
        if (foundUser != null && passwordEncoder.matches(userLoginDTO.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Logado exitosamente"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Credenciales inválidas"));
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            
            if (user != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Profile retrieved successfully.");
                response.put("username", user.getUsername());
                response.put("email", user.getEmail());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User not found."));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "User is not authenticated."));
        }
    }
}












/* 
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Usuario con este username ya existe."));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "Usuario registrado satisfactoriamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Logado exitosamente"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Credenciales inválidas"));
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            // Usa la instancia inyectada del repositorio
            User user = userRepository.findByUsername(username);
            
            if (user != null) {
                // Devuelve los detalles del usuario junto con un mensaje de éxito
                Map<String, String> response = new HashMap<>();
                response.put("message", "Profile retrieved successfully.");
                response.put("username", user.getUsername());
                response.put("email", user.getEmail());
                // Agrega otros detalles del usuario si es necesario
                return ResponseEntity.ok(response);
            } else {
                // Devuelve un mensaje de error si el usuario no se encuentra
                Map<String, String> response = Collections.singletonMap("message", "User not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } else {
            // Devuelve un mensaje de error si el usuario no está autenticado
            Map<String, String> response = Collections.singletonMap("message", "User is not authenticated.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
} 

*/
