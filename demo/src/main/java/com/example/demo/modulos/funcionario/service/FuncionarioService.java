package com.example.demo.modulos.funcionario.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.funcionario.dto.FuncionarioRequest;
import com.example.demo.modulos.funcionario.dto.FuncionarioResponse;
import com.example.demo.modulos.funcionario.model.Funcionario;
import com.example.demo.modulos.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.modulos.comum.util.DateUtil.validarAnteriorAoPeriodo;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;

    public List<FuncionarioResponse> listarFuncionarios() {
        return repository.findAll().stream().map(FuncionarioResponse::of).toList();
    }

    public FuncionarioResponse criar(FuncionarioResponse request) {
        validarloginExistente(request.login());
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        var funcionario = Funcionario.of(request);
        return FuncionarioResponse.of(repository.save(funcionario));
    }

    public FuncionarioResponse atualizar(Integer id, FuncionarioResponse request) {
        validarFuncionarioExistente(id);
        validarloginExistente(request.login(), id);
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        var funcionario = Funcionario.of(request);
        funcionario.setId(id);
        return FuncionarioResponse.of(repository.save(funcionario));
    }

    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ValidacaoException("Não foi possivel excluir funcionario. " .concat(ex.getMessage()));
        }
    }

    public int qtdFuncionarios() {
        return repository.findAll().size();
    }

    public FuncionarioResponse findByLoginAndSenha(String login, String senha) {
        return FuncionarioResponse.of(repository.findByLoginAndSenha(login, senha));
    }

    private void validarFuncionarioExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Funcionario não encontrado.");
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