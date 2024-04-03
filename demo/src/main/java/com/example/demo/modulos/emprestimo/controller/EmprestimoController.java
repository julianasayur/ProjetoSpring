package com.example.demo.modulos.emprestimo.controller;

import com.example.demo.modulos.emprestimo.dto.EmprestimoRequest;
import com.example.demo.modulos.emprestimo.dto.EmprestimoResponse;
import com.example.demo.modulos.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @GetMapping
    public List<EmprestimoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponse criar(@Valid @RequestBody EmprestimoRequest request) {
        return service.criar(request);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping(value = "/findTotaisAnoMes")
    public List<?> findTotaisAnoMes() {
        return service.findTotaisAnoMes();
    }

    @GetMapping(value = "/findByCliente/{idCliente}")
    public List<EmprestimoResponse> findByCliente(@PathVariable Integer idCliente) {
        return service.findByClienteId(idCliente);
    }

    @GetMapping(value = "/findByClienteAndPeriodo/{idCliente}/{inicio}/{termino}")
    public List<EmprestimoResponse> findByClienteAndPeriodo(@PathVariable Integer idCliente,
                                                            @PathVariable String inicio,
                                                            @PathVariable String termino) {
        return service.findByClienteAndPeriodo(idCliente, inicio, termino);
    }

    @GetMapping(value = "/findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo/{inicio}/{termino}")
    public List<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        return service.findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(inicio, termino);
    }

    @GetMapping(value = "/findQuantidadesEmprestimosOfFilmesByPeriodo/{inicio}/{termino}")
    public List<?> findQuantidadesEmprestimosOfFilmesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        return service.findQuantidadesEmprestimosOfFilmesByPeriodo(inicio, termino);
    }
}
