package com.example.demo.modulos.emprestimo.controller;

import com.example.demo.modulos.emprestimo.dto.ReservaRequest;
import com.example.demo.modulos.emprestimo.dto.ReservaResponse;
import com.example.demo.modulos.emprestimo.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<ReservaResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ReservaResponse criar(@Valid @RequestBody ReservaRequest request) {
        return service.criar(request);
    }

    @PutMapping(value = "/{id}")
    public ReservaResponse atualizar(@Valid @RequestBody ReservaRequest request, @PathVariable Integer id) {
        return service.atualizar(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/findByClienteAndPeriodo/{idCliente}/{inicio}/{termino}")
    public List<ReservaResponse> findByClienteAndPeriodo(@PathVariable Integer idCliente, @PathVariable String inicio,
                                                         @PathVariable String termino) {
        return service.findByClienteAndPeriodo(idCliente, inicio, termino);
    }

    @GetMapping(value = "/findQuantidadesReservasOfClientesByPeriodo/{inicio}/{termino}")
    public List<?> findQuantidadesReservasOfClientesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        return service.findQuantidadesReservasOfClientesByPeriodo(inicio, termino);
    }
}
