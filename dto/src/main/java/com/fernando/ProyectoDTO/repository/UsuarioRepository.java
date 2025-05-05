package com.fernando.ProyectoDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.ProyectoDTO.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
}
