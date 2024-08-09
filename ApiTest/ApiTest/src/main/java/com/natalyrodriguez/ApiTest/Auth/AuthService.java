package com.natalyrodriguez.ApiTest.Auth;

import org.springframework.stereotype.Service;

import com.natalyrodriguez.ApiTest.Jwt.JwtService;
import com.natalyrodriguez.ApiTest.model.Role;
import com.natalyrodriguez.ApiTest.model.User;
import com.natalyrodriguez.ApiTest.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(ResgisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .phonenumber(request.getPhonenumber())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
