package com.natalyrodriguez.ApiTest.repository;

import com.natalyrodriguez.ApiTest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Anotación que indica que esta es una prueba de JPA, que configura una base de
             // datos en memoria para la prueba
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // Crea y guarda un nuevo usuario
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        userRepository.save(user);

        // Busca al usuario por su nombre de usuario
        User foundUser = userRepository.findByUsername("testuser");

        // Verifica que el usuario encontrado no sea nulo y tenga el nombre esperado
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }
}
