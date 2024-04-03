package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservaRequest(
        @DatePattern
        LocalDate data,
        @NotNull
        Integer status,
        @NotNull
        Integer clienteId,
        @NotNull
        Integer livroFisicoId
) {
}
