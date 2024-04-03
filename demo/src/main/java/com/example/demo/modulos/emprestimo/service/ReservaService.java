package com.example.demo.modulos.emprestimo.service;

import com.example.demo.modulos.cliente.service.ClienteService;
import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.emprestimo.dto.ReservaRequest;
import com.example.demo.modulos.emprestimo.dto.ReservaResponse;
import com.example.demo.modulos.emprestimo.model.Reserva;
import com.example.demo.modulos.emprestimo.repository.ReservaRepository;
import com.example.demo.modulos.livro.service.LivroFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;
    @Autowired
    private LivroFisicoService livroFisicoService;
    @Autowired
    private ClienteService clienteService;

    public Reserva findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Reserva nao encontrada"));
    }

    public List<ReservaResponse> listar() {
        return repository.findAll().stream().map(ReservaResponse::of).toList();
    }

    public ReservaResponse criar(ReservaRequest request) {
        var cliente = clienteService.findById(request.clienteId());
        var livroFisico = livroFisicoService.findById(request.livroFisicoId());
        var reserva = Reserva.of(request, cliente, livroFisico);
        return ReservaResponse.of(repository.save(reserva));
    }

    public ReservaResponse atualizar(ReservaRequest request, Integer id) {
        validarExistente(id);
        var cliente = clienteService.findById(request.clienteId());
        var livroFisico = livroFisicoService.findById(request.livroFisicoId());
        var reserva = Reserva.of(request, cliente, livroFisico);
        reserva.setId(id);
        return ReservaResponse.of(repository.save(reserva));
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

    public List<ReservaResponse> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
        return repository.findByClienteAndPeriodo(idCliente, inicio, termino)
                .stream().map(ReservaResponse::of).toList();
    }

    public List<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino) {
        return repository.findQuantidadesReservasOfClientesByPeriodo(inicio, termino);
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Reserva não encontrada.");
        }
    }
}
