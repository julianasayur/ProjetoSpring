package com.example.demo.modulos.emprestimo.controller;

import com.example.demo.modulos.emprestimo.dto.DevolucaoRequest;
import com.example.demo.modulos.emprestimo.dto.DevolucaoResponse;
import com.example.demo.modulos.emprestimo.service.DevolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/devolucao")
public class DevolucaoController {

    @Autowired
    private DevolucaoService service;

    @GetMapping
    public List<DevolucaoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DevolucaoResponse criar(@Valid @RequestBody DevolucaoRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public DevolucaoResponse atualizar(@Valid @RequestBody DevolucaoRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/findByClienteAndPeriodo/{idCliente}/{inicio}/{termino}")
    public List<DevolucaoResponse> findByClienteAndPeriodo(@PathVariable Integer idCliente,
                                                           @PathVariable String inicio, @PathVariable String termino) {
        return service.findByClienteAndPeriodo(idCliente, inicio, termino);
    }

    @GetMapping(value = "/findQuantidadeDevolucaoClienteByPeriodo/{inicio}/{termino}")
    public List<?> findQuantidadeDevolucaoClienteByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        return service.findQuantidadeDevolucaoClienteByPeriodo(inicio, termino);
    }
}
