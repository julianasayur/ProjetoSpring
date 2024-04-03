package com.example.demo.modulos.emprestimo.service;
import com.example.demo.modulos.comum.exception.NotFoundException;
import com.example.demo.modulos.emprestimo.dto.ItemDeEmprestimoRequest;
import com.example.demo.modulos.emprestimo.model.ItemDeEmprestimo;
import com.example.demo.modulos.emprestimo.repository.ItemDeEmprestimoRepository;
import com.example.demo.modulos.livro.service.LivroFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemDeEmprestimoService {

    @Autowired
    private ItemDeEmprestimoRepository repository;
    @Autowired
    private LivroFisicoService livroFisicoService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ItemDeEmprestimo> saveAll(List<ItemDeEmprestimoRequest> request) {
        var itens = request.stream().map(item ->
                ItemDeEmprestimo.of(item, livroFisicoService.findById(item.livroFisicoId()))).toList();
        return repository.saveAll(itens);
    }

    public ItemDeEmprestimo findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ItemDeEmprestimo nao encontrado"));
    }

    public List<ItemDeEmprestimo> findById(List<Integer> id) {
        var lista = repository.findAllById(id);
        if (lista.isEmpty()) {
            throw new NotFoundException("Nenhum ItemDeEmprestimo foi encontrado");
        }
        return lista;
    }
}
