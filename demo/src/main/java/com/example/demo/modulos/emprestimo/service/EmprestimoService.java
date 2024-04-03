package com.example.demo.modulos.emprestimo.service;

import com.example.demo.modulos.cliente.service.ClienteService;
import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.comum.exception.ValidacaoException;
import com.example.demo.modulos.emprestimo.dto.EmprestimoRequest;
import com.example.demo.modulos.emprestimo.dto.EmprestimoResponse;
import com.example.demo.modulos.emprestimo.dto.ItemDeEmprestimoResponse;
import com.example.demo.modulos.emprestimo.model.Emprestimo;
import com.example.demo.modulos.emprestimo.model.ItemDeEmprestimo;
import com.example.demo.modulos.emprestimo.repository.EmprestimoRepository;
import com.example.demo.modulos.livro.service.LivroFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LivroFisicoService livroFisicoService;
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ItemDeEmprestimoService itemDeEmprestimoService;

    public Emprestimo findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Emprestimo nao encontrado"));
    }

    public List<EmprestimoResponse> listar() {
        return repository.findAll().stream()
                .map(EmprestimoResponse::of)
                .toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EmprestimoResponse criar(EmprestimoRequest request) {
        var itens = itemDeEmprestimoService.saveAll(request.itemDeEmprestimoRequests());
        validarFilmesNaoDisponiveis(itens);
        var itensResponse = itens.stream().map(ItemDeEmprestimoResponse::of).toList();
        var cliente = clienteService.findById(request.clienteId());
        var emprestimo = Emprestimo.of(request, cliente);
        return EmprestimoResponse.of(repository.save(emprestimo), itensResponse);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ValidacaoException("Não é possível excluir, existem itens vinculados!");
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao deletar Gerente. " .concat(ex.getMessage()));
        }
    }

    public List<EmprestimoResponse> findByClienteId(Integer clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(EmprestimoResponse::of).toList();
    }

    public List<EmprestimoResponse> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
        return repository.findByClienteAndPeriodo(idCliente, inicio, termino)
                .stream()
                .map(EmprestimoResponse::of).toList();
    }

    public List<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(String inicio, String termino) {
        return repository.findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(inicio, termino);
    }

    public List<?> findQuantidadesEmprestimosOfFilmesByPeriodo(String inicio, String termino) {
        return repository.findQuantidadesEmprestimosOfLivroByPeriodo(inicio, termino);
    }

    public List<?> findTotaisAnoMes() {
        return repository.findTotaisAnoMes();
    }

    private void validarExistente(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("item não encontrado.");
        }
    }

    private void validarFilmesNaoDisponiveis(List<ItemDeEmprestimo> itens) {
        itens.forEach(item -> livroFisicoService.validarLivrosDisponiveis(item.getId()));
    }
}
