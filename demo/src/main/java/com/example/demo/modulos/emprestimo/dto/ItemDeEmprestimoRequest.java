package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ItemDeEmprestimoRequest(
        Integer id,
        @NotNull
        Double valor,
        @NotNull
        @DatePattern
        LocalDate data,
        @NotNull
        Integer livroFisicoId,
        Integer emprestimoId
) {
}
