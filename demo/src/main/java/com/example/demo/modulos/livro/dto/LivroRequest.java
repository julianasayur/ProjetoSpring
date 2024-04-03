package com.example.demo.modulos.livro.dto;

import com.example.demo.modulos.livro.model.TipoDeLivro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequest(
        @NotBlank
        String titulo,
        @NotBlank
        String genero,
        @NotNull
        Integer tipoDeLivroId
) {
}