package com.biblioteca.api.application.security.service;

import com.biblioteca.api.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Se crea el servicio para autenticar un usuario
 */
@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    /**
     * @param username carga los datos del usuario a traves del atributo username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepository.findByUsername(username);
    }
}
