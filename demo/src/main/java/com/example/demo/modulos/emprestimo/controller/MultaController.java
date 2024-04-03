package com.example.demo.modulos.emprestimo.controller;

import com.example.demo.modulos.emprestimo.dto.MultaRequest;
import com.example.demo.modulos.emprestimo.dto.MultaResponse;
import com.example.demo.modulos.emprestimo.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/multa")
public class MultaController {

    @Autowired
    private MultaService service;

    @GetMapping
    public List<MultaResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MultaResponse criar(@Valid @RequestBody MultaRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public MultaResponse atualizar(@Valid @RequestBody MultaRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
