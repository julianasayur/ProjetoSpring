package com.example.demo.modulos.livro.dto;

import com.example.demo.modulos.livro.model.TipoDeLivro;

public record TipoDeLivroResponse(
        Integer id,
        String nome,
        Integer prazo,
        Double preco
) {
    public static TipoDeLivroResponse of(TipoDeLivro tipoDeLivro) {
        return new TipoDeLivroResponse(tipoDeLivro.getId(), tipoDeLivro.getNome(), tipoDeLivro.getPrazo(),
                tipoDeLivro.getPreco());
    }
}
