package com.example.demo.modulos.cliente.service;

import com.example.demo.modulos.cliente.dto.ClienteRequest;
import com.example.demo.modulos.cliente.dto.ClienteResponse;
import com.example.demo.modulos.cliente.model.Cliente;
import com.example.demo.modulos.cliente.repository.ClienteRepository;
import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.modulos.comum.util.DateUtil.validarAnteriorAoPeriodo;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente nao encontrado"));
    }

    public List<ClienteResponse> listar() {
        return repository.findAll().stream().map(ClienteResponse::of).toList();
    }

    public ClienteResponse criar(ClienteRequest request) {
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        validarCpfExistente(request.cpf());
        var cliente = Cliente.of(request);
        return ClienteResponse.of(repository.save(cliente));
    }

    public ClienteResponse atualizar(ClienteRequest request, Integer id) {
        validarGerenteExistente(id);
        validarCpfExistente(request.cpf(), id);
        validarAnteriorAoPeriodo(request.dataNascimento(),
                LocalDate.now(), "A data de nascimento nao pode ser maior que a data atual");
        var cliente = Cliente.of(request);
        cliente.setId(id);
        return ClienteResponse.of(repository.save(cliente));
    }

    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ValidacaoException("Não é possível excluir, existem itens vinculados!");
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao deletar Gerente. " .concat(ex.getMessage()));
        }
    }

    private void validarGerenteExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Gerente não encontrado.");
        }
    }

    private void validarCpfExistente(String cpf) {
        if (repository.existsByCpfIgnoreCase(cpf)) {
            throw new ValidacaoException("Item com esse cpf já existe.");
        }
    }

    private void validarCpfExistente(String cpf, Integer id) {
        if (repository.existsByCpfIgnoreCaseAndIdNot(cpf, id)) {
            throw new ValidacaoException("Item com esse cpf já existe.");
        }
    }
}
