package com.example.demo.modulos.funcionario.controller;

import com.example.demo.modulos.funcionario.model.Gerente;
import com.example.demo.modulos.funcionario.repository.GerenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    GerenteRepository gerenteRepository;

    @GetMapping
    public List<Gerente> listarGerentes () {
        return gerenteRepository.findAll();
    }

    @PostMapping
    public Gerente criar (@RequestBody Gerente gerente) {
        return gerenteRepository.save(gerente);
    }
}
