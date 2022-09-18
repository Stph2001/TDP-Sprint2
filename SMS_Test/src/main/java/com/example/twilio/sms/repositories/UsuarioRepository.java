package com.example.twilio.sms.repositories;

import com.example.twilio.sms.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByCorreoAndClave(String correoUsuario, String claveUsuario);
    Optional<Usuario> findByCel(String celUsuario);
    Optional<Usuario> findByCorreo(String correoUsuario);
}
