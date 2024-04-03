package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.emprestimo.model.Emprestimo;

import java.time.LocalDate;
import java.util.List;

public record EmprestimoResponse(
        Integer id,
        @DatePattern
        LocalDate data,
        Double valor,
        String clienteNome,
        String clienteTelefone,
        List<ItemDeEmprestimoResponse> itens
) {
    public static EmprestimoResponse of(Emprestimo emprestimo, List<ItemDeEmprestimoResponse> emprestimoItens) {
        return new EmprestimoResponse(emprestimo.getId(), emprestimo.getData(), emprestimo.getValor(),
                emprestimo.getCliente().getNome(), emprestimo.getCliente().getTelefone(), emprestimoItens);
    }

    public static EmprestimoResponse of(Emprestimo emprestimo) {
        return new EmprestimoResponse(emprestimo.getId(), emprestimo.getData(), emprestimo.getValor(),
                emprestimo.getCliente().getNome(), emprestimo.getCliente().getTelefone(), null);
    }
}
