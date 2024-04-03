package com.example.demo.modulos.livro.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.livro.dto.LivroRequest;
import com.example.demo.modulos.livro.dto.LivroResponse;
import com.example.demo.modulos.livro.model.Livro;
import com.example.demo.modulos.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private TipoDeLivroService tipoDeLivroService;

    public Livro findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Livro nao encontrado"));
    }

    @Transactional
    public List<LivroResponse> listar() {
        return repository.findAll().stream()
                .map(LivroResponse::of).toList();
    }

    public LivroResponse criar(LivroRequest request) {
        validarNomeExistente(request.titulo());
        var tipoLivro = tipoDeLivroService.findById(request.tipoDeLivroId());
        var livro = Livro.of(request, tipoLivro);
        return LivroResponse.of(repository.save(livro));
    }

    public LivroResponse atualizar(LivroRequest request, Integer id) {
        validarExistente(id);
        validarNomeExistente(request.titulo(), id);
        var tipoLivro = tipoDeLivroService.findById(request.tipoDeLivroId());
        var livro = Livro.of(request, tipoLivro);
        livro.setId(id);
        return LivroResponse.of(repository.save(livro));
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

    public List<LivroResponse> buscarTipoDeLivro(Integer tipoLivroId) {
        return repository.findByTipoDeLivroId(tipoLivroId)
                .stream().map(LivroResponse::of).toList();
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }

    private void validarNomeExistente(String nome) {
        if (repository.existsByTituloIgnoreCase(nome)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }

    private void validarNomeExistente(String nome, Integer id) {
        if (repository.existsByTituloIgnoreCaseAndIdNot(nome, id)) {
            throw new ValidacaoException("Item com esse nome já existe.");
        }
    }
}
