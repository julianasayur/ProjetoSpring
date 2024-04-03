package com.example.demo.modulos.emprestimo.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.emprestimo.dto.MultaRequest;
import com.example.demo.modulos.emprestimo.dto.MultaResponse;
import com.example.demo.modulos.emprestimo.model.Multa;
import com.example.demo.modulos.emprestimo.repository.MultaRepository;
import com.example.demo.modulos.livro.service.LivroFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService {

    @Autowired
    private MultaRepository repository;
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private LivroFisicoService livroFisicoService;
    @Autowired
    private ItemDeEmprestimoService itemDeEmprestimoService;

    public Multa findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Multa nao encontrada"));
    }

    public List<MultaResponse> listar() {
        return repository.findAll().stream()
                .map(MultaResponse::of).toList();
    }

    public MultaResponse criar(MultaRequest request) {
        var item = itemDeEmprestimoService.findById(request.itemDeEmprestimoId());
        var multa = Multa.of(request, item);
        return MultaResponse.of(repository.save(multa));
    }

    public MultaResponse atualizar(MultaRequest request, Integer id) {
        validarExistente(id);
        var item = itemDeEmprestimoService.findById(request.itemDeEmprestimoId());
        var multa = Multa.of(request, item);
        multa.setId(id);
        return MultaResponse.of(repository.save(multa));
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

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }
}
