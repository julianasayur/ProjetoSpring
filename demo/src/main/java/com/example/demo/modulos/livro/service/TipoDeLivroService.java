package com.example.demo.modulos.livro.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.livro.dto.TipoDeLivroRequest;
import com.example.demo.modulos.livro.dto.TipoDeLivroResponse;
import com.example.demo.modulos.livro.model.TipoDeLivro;
import com.example.demo.modulos.livro.repository.TipoDeLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDeLivroService {

    @Autowired
    private TipoDeLivroRepository repository;

    public TipoDeLivro findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de Livro nao encontrado"));
    }

    public List<TipoDeLivroResponse> listar() {
        return repository.findAll().stream().map(TipoDeLivroResponse::of).toList();
    }

    public TipoDeLivroResponse criar(TipoDeLivroRequest request) {
        validarNomeExistente(request.nome());
        var livro = TipoDeLivro.of(request);
        return TipoDeLivroResponse.of(repository.save(livro));
    }

    public TipoDeLivroResponse atualizar(TipoDeLivroRequest request, Integer id) {
        validarExistente(id);
        validarNomeExistente(request.nome(), id);
        var livro = TipoDeLivro.of(request);
        livro.setId(id);
        return TipoDeLivroResponse.of(repository.save(livro));
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

    public List<TipoDeLivroResponse> findMaioresPrecos(Double preco) {
        return repository.findMaioresPrecos(preco).stream().map(TipoDeLivroResponse::of).toList();
    }

    public List<TipoDeLivroResponse> findMenoresPrecos(Double preco) {
        return repository.findMenoresPrecos(preco).stream().map(TipoDeLivroResponse::of).toList();
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }

    private void validarNomeExistente(String nome) {
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }

    private void validarNomeExistente(String nome, Integer id) {
        if (repository.existsByNomeIgnoreCaseAndIdNot(nome, id)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }
}
