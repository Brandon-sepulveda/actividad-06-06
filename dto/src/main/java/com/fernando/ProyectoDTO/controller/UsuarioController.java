package com.fernando.ProyectoDTO.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.ProyectoDTO.dto.UsuarioDTO;
import com.fernando.ProyectoDTO.model.Rol;
import com.fernando.ProyectoDTO.model.Usuario;
import com.fernando.ProyectoDTO.repository.RolRepository;
import com.fernando.ProyectoDTO.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    // MÉTODO PARA CREAR UN USUARIO
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // MÉTODO PARA OBTENER LOS USUARIOS SIN EXPONER LA CONTRASEÑA, UTILIZANDO DTO
    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getCorreo(),
                        usuario.getNombre(),
                        usuario.getApellido()))
                .collect(Collectors.toList());
    }

    @PostMapping("{correo}/asignar/{rolID}")
    public ResponseEntity<String> asignar(@PathVariable String correo,@PathVariable int rolID){
        if(usuarioRepository.existsById(correo) && rolRepository.existsById(rolID)){
            Usuario usuario = usuarioRepository.findById(correo).get();
            Rol rol = rolRepository.findById(rolID).get();

            usuario.getRoles().add(rol);        
            usuarioRepository.save(usuario);

            return ResponseEntity.ok("Rol asignado correctamente");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol o usuario no encontrado");
        }
    }
}
