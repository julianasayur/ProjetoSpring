package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.emprestimo.model.ItemDeEmprestimo;

import java.time.LocalDate;

public record ItemDeEmprestimoResponse(
        Integer id,
        Double valor,
        @DatePattern
        LocalDate data,
        Integer livroFisicoId,
        String livroTitulo
) {
    public static ItemDeEmprestimoResponse of(ItemDeEmprestimo itemDeEmprestimo) {
        return new ItemDeEmprestimoResponse(itemDeEmprestimo.getId(), itemDeEmprestimo.getValor(),
                itemDeEmprestimo.getData(), itemDeEmprestimo.getLivroFisico().getId(),
                itemDeEmprestimo.getLivroFisico().getLivro().getTitulo());
    }
}