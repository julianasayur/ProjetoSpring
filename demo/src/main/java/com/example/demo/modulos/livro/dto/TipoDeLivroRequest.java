package com.example.demo.modulos.livro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoDeLivroRequest(
        @NotBlank
        String nome,
        @NotNull
        Integer prazo,
        @NotNull
        Double preco
) {
}
