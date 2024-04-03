package com.example.demo.modulos.livro.controller;

import com.example.demo.modulos.livro.dto.LivroRequest;
import com.example.demo.modulos.livro.dto.LivroResponse;
import com.example.demo.modulos.livro.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<LivroResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criar(@Valid @RequestBody LivroRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public LivroResponse atualizar(@Valid @RequestBody LivroRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/findByTipoDeFilme/{idTipoLivro}")
    public List<LivroResponse> buscarTipoDeLivro(@PathVariable Integer idTipoLivro) {
        return service.buscarTipoDeLivro(idTipoLivro);
    }
}
