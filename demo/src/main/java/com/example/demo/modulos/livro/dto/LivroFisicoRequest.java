package com.example.demo.modulos.livro.dto;

import jakarta.validation.constraints.NotNull;

public record LivroFisicoRequest(
        boolean danificada,
        boolean disponivel,
        @NotNull
        Integer livroId
) {
}
