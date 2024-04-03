package com.example.demo.modulos.livro.controller;

import com.example.demo.modulos.livro.dto.TipoDeLivroRequest;
import com.example.demo.modulos.livro.dto.TipoDeLivroResponse;
import com.example.demo.modulos.livro.service.TipoDeLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tipo-livro")
public class TipoDeLivroController {

    @Autowired
    private TipoDeLivroService service;

    @GetMapping
    public List<TipoDeLivroResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoDeLivroResponse criar(@Valid @RequestBody TipoDeLivroRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public TipoDeLivroResponse atualizar(@Valid @RequestBody TipoDeLivroRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/maiores-precos/{preco}")
    public List<TipoDeLivroResponse> findMaioresPrecos(@PathVariable Double preco) {
        return service.findMaioresPrecos(preco);
    }

    @GetMapping(value = "/menores-precos/{preco}")
    public List<TipoDeLivroResponse> findMenoresPrecos(@PathVariable Double preco) {
        return service.findMenoresPrecos(preco);
    }
}
