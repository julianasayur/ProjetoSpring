package com.example.demo.modulos.funcionario.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record FuncionarioRequest(
        @NotBlank
        String nome,
        @CPF
        @NotBlank
        String cpf,
        @NotNull
        @DatePattern
        LocalDate dataNascimento,
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}