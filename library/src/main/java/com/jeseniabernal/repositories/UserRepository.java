package com.jeseniabernal.repositories;

import com.jeseniabernal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username el nombre de usuario a buscar
     * @return el usuario encontrado, o null si no existe
     */
    User findByUsername(String username);
}
