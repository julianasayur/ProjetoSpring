package com.example.demo.modulos.livro.controller;

import com.example.demo.modulos.livro.dto.LivroFisicoRequest;
import com.example.demo.modulos.livro.dto.LivroFisicoResponse;
import com.example.demo.modulos.livro.service.LivroFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/livro-fisico")
public class LivroFisicoController {

    @Autowired
    private LivroFisicoService service;

    @GetMapping
    public List<LivroFisicoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroFisicoResponse criar(@Valid @RequestBody LivroFisicoRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public LivroFisicoResponse atualizar(@Valid @RequestBody LivroFisicoRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/findByDanificadaAndDisponivel/{danificada}/{disponivel}")
    public List<LivroFisicoResponse> findByDanificadaAndDisponivel(@PathVariable Boolean danificada,
                                                                   @PathVariable Boolean disponivel) {
        return service.findByDanificadaAndDisponivel(danificada, disponivel);
    }

    @GetMapping(value = "/findByFilme/{livroId}")
    public List<LivroFisicoResponse> buscarPorLivroId(@PathVariable Integer livroId) {
        return service.findByLivroId(livroId);
    }
}
