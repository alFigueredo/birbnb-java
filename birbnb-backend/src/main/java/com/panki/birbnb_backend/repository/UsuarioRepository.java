package com.panki.birbnb_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panki.birbnb_backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
