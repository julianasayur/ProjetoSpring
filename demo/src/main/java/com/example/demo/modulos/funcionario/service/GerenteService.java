package com.example.demo.modulos.funcionario.service;


import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.funcionario.dto.GerenteRequest;
import com.example.demo.modulos.funcionario.dto.GerenteResponse;
import com.example.demo.modulos.funcionario.model.Gerente;
import com.example.demo.modulos.funcionario.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.modulos.comum.util.DateUtil.validarAnteriorAoPeriodo;

@Service
public class GerenteService {

    @Autowired
    GerenteRepository repository;

    public List<GerenteResponse> listar() {
        return repository.findAll().stream().map(GerenteResponse::of).toList();
    }

    public GerenteResponse criar(GerenteRequest request) {
        validarloginExistente(request.login());
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        var gerente = Gerente.of(request);
        return GerenteResponse.of(repository.save(gerente));
    }

    public GerenteResponse atualizar(GerenteRequest request, Integer id) {
        validarGerenteExistente(id);
        validarloginExistente(request.login(), id);
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        var gerente = Gerente.of(request);
        gerente.setId(id);
        return GerenteResponse.of(repository.save(gerente));
    }

    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao deletar Gerente. " .concat(ex.getMessage()));
        }
    }

    private void validarGerenteExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Gerente não encontrado.");
        }
    }

    private void validarloginExistente(String login) {
        if (repository.existsByLoginIgnoreCase(login)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }

    private void validarloginExistente(String login, Integer id) {
        if (repository.existsByLoginIgnoreCaseAndIdNot(login, id)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }
}