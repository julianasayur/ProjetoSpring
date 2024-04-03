package com.example.demo.modulos.livro.dto;

import com.example.demo.modulos.livro.model.Livro;

public record LivroResponse(
        Integer id,
        String titulo,
        String genero,
        String tipoDeLivroNome
) {
    public static LivroResponse of(Livro livro) {
        return new LivroResponse(livro.getId(), livro.getTitulo(),
                livro.getGenero(), livro.getTipoDeLivro().getNome());
    }
}