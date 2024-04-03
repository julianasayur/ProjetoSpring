package com.example.demo.modulos.funcionario.controller;

import com.example.demo.modulos.funcionario.dto.GerenteRequest;
import com.example.demo.modulos.funcionario.dto.GerenteResponse;
import com.example.demo.modulos.funcionario.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    GerenteService service;

    @GetMapping
    public List<GerenteResponse> listarGerentes() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GerenteResponse criar(@Valid @RequestBody GerenteRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    public GerenteResponse atualizar(@Valid @RequestBody GerenteRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}