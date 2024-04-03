package com.example.demo.modulos.cliente.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @DatePattern
        LocalDate dataNascimento,
        @NotNull
        Double debito,
        @NotBlank
        String telefone
) {
}
