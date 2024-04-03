package com.example.demo.modulos.emprestimo.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.emprestimo.dto.DevolucaoRequest;
import com.example.demo.modulos.emprestimo.dto.DevolucaoResponse;
import com.example.demo.modulos.emprestimo.model.Devolucao;
import com.example.demo.modulos.emprestimo.repository.DevolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevolucaoService {

    @Autowired
    private DevolucaoRepository repository;
    @Autowired
    private ItemDeEmprestimoService itemDeEmprestimoService;

    public Devolucao findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Devolucao nao encontrada"));
    }

    public List<DevolucaoResponse> listar() {
        return repository.findAll().stream()
                .map(DevolucaoResponse::of).toList();
    }

    public DevolucaoResponse criar(DevolucaoRequest request) {
        var itemDeEmprestimo = itemDeEmprestimoService.findById(request.itemDeEmprestimoId());
        var devolucao = Devolucao.of(request, itemDeEmprestimo);
        return DevolucaoResponse.of(repository.save(devolucao));
    }

    public DevolucaoResponse atualizar(DevolucaoRequest request, Integer id) {
        validarExistente(id);
        var itemDeEmprestimo = itemDeEmprestimoService.findById(request.itemDeEmprestimoId());
        var devolucao = Devolucao.of(request, itemDeEmprestimo);
        devolucao.setId(id);
        return DevolucaoResponse.of(repository.save(devolucao));
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

    public List<DevolucaoResponse> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
        return repository.findByClienteAndPeriodo(idCliente, inicio, termino).stream()
                .map(DevolucaoResponse::of).toList();
    }

    public List<?> findQuantidadeDevolucaoClienteByPeriodo(String inicio, String termino) {
        return repository.findQuantidadeDevolucaoClienteByPeriodo(inicio, termino);
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }
}
