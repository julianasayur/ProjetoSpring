package com.example.demo.modulos.funcionario.controller;

import com.example.demo.modulos.funcionario.dto.FuncionarioRequest;
import com.example.demo.modulos.funcionario.dto.FuncionarioResponse;
import com.example.demo.modulos.funcionario.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService service;

    @GetMapping
    public List<FuncionarioResponse> listarFuncionarios() {
        return service.listarFuncionarios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponse criar(@Valid @RequestBody FuncionarioResponse request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    public FuncionarioResponse atualizar(@PathVariable Integer id, @Valid @RequestBody FuncionarioResponse request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping("/qtd-funcionarios")
    public int qtdFuncionarios() {
        return service.qtdFuncionarios();
    }

    @GetMapping(value = "/{login}/{senha}")
    public FuncionarioResponse findByLoginAndSenha(@PathVariable String login, @PathVariable String senha) {
        return service.findByLoginAndSenha(login, senha);
    }
}
