package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.emprestimo.model.Devolucao;

import java.time.LocalDate;

public record DevolucaoResponse(
        Integer id,
        @DatePattern
        LocalDate data,
        Double itemDeEmprestimoValor,
        LocalDate itemDeEmprestimoData,
        Integer livroFisicoId,
        String livroTitulo
) {
    public static DevolucaoResponse of(Devolucao devolucao) {
        return new DevolucaoResponse(devolucao.getId(), devolucao.getData(),
                devolucao.getItemDeEmprestimo().getValor(), devolucao.getItemDeEmprestimo().getData(),
                devolucao.getItemDeEmprestimo().getLivroFisico().getId(),
                devolucao.getItemDeEmprestimo().getLivroFisico().getLivro().getTitulo());
    }
}
