package com.movexa.api.repository;

import com.movexa.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para autenticação
    Optional<Usuario> findByEmailAndSenhaHash(String email, String senhaHash);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
