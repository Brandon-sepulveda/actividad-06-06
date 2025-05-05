package com.fernando.ProyectoDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.ProyectoDTO.model.Rol;
import com.fernando.ProyectoDTO.repository.RolRepository;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository repository;

    @PostMapping
    public Rol almacenar(@RequestBody Rol rol){
        return repository.save(rol);
    }

    @GetMapping
    public List<Rol> listar (){
        return repository.findAll();
    }

}
