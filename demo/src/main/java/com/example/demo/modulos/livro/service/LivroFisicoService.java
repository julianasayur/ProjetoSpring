package com.example.demo.modulos.livro.service;

import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.livro.dto.LivroFisicoRequest;
import com.example.demo.modulos.livro.dto.LivroFisicoResponse;
import com.example.demo.modulos.livro.model.LivroFisico;
import com.example.demo.modulos.livro.repository.LivroFisicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroFisicoService {

    @Autowired
    private LivroFisicoRepository repository;
    @Autowired
    private LivroService livroService;

    public LivroFisico findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Livro fisico nao encontrado"));
    }

    public List<LivroFisicoResponse> listar() {
        return repository.findAll().stream().map(LivroFisicoResponse::of).toList();
    }

    public List<LivroFisicoResponse> findByDanificadaAndDisponivel(Boolean danificada, Boolean disponivel) {
        return repository.findByDanificadaAndDisponivel(danificada, disponivel)
                .stream().map(LivroFisicoResponse::of).toList();
    }

    public LivroFisicoResponse criar(LivroFisicoRequest request) {
        var livro = livroService.findById(request.livroId());
        var livroFisico = repository.save(LivroFisico.of(request, livro));
        return LivroFisicoResponse.of(livroFisico);
    }

    public LivroFisicoResponse atualizar(LivroFisicoRequest request, Integer id) {
        validarExistente(id);
        var livro = livroService.findById(request.livroId());
        var livroFisico = LivroFisico.of(request, livro);
        livroFisico.setId(id);
        return LivroFisicoResponse.of(repository.save(livroFisico));
    }

    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao deletar item. " .concat(ex.getMessage()));
        }
    }

    public List<LivroFisicoResponse> findByLivroId(Integer livroId) {
        return repository.findByLivro_Id(livroId).stream().map(LivroFisicoResponse::of).toList();
    }

    public void validarLivrosDisponiveis(Integer id) {
        if (repository.existsLivroFisicoByIdAndDisponivelFalse(id)) {
            throw new ValidacaoException("Livro fisico com id " .concat(id.toString())
                    .concat(" não disponível para empréstimo!"));
        }
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }
}
