package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.emprestimo.model.Multa;

import java.time.LocalDate;

public record MultaResponse(
        Integer id,
        Double valor,
        boolean pago,
        Double itemDeEmprestimoValor,
        LocalDate itemDeEmprestimoData,
        Integer livroFisicoId,
        String livroTitulo
) {

    public static MultaResponse of(Multa multa) {
        return new MultaResponse(multa.getId(), multa.getValor(),
                multa.isPago(), multa.getItemDeEmprestimo().getValor(), multa.getItemDeEmprestimo().getData(),
                multa.getItemDeEmprestimo().getLivroFisico().getId(),
                multa.getItemDeEmprestimo().getLivroFisico().getLivro().getTitulo());
    }
}
