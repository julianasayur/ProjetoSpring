package com.example.demo.modulos.cliente.controller;

import com.example.demo.modulos.cliente.dto.ClienteRequest;
import com.example.demo.modulos.cliente.dto.ClienteResponse;
import com.example.demo.modulos.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<ClienteResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@Valid @RequestBody ClienteRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@Valid @RequestBody ClienteRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
