package com.example.demo.modulos.livro.dto;

import com.example.demo.modulos.livro.model.LivroFisico;

public record LivroFisicoResponse(
        Integer id,
        boolean danificada,
        boolean disponivel,
        String livroTitulo
) {
    public static LivroFisicoResponse of(LivroFisico livroFisico) {
        return new LivroFisicoResponse(livroFisico.getId(), livroFisico.getDanificada(),
                livroFisico.getDisponivel(), livroFisico.getLivro().getTitulo());
    }
}
