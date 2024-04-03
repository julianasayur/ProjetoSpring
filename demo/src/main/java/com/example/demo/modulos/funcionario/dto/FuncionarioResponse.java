package com.example.demo.modulos.funcionario.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.funcionario.model.Funcionario;

import java.time.LocalDate;

public record FuncionarioResponse(
        Integer id,
        String nome,
        String cpf,
        @DatePattern
        LocalDate dataNascimento,
        String login,
        String senha
) {
    public static FuncionarioResponse of(Funcionario funcionario) {
        return new FuncionarioResponse(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(),
                funcionario.getDataNascimento(), funcionario.getLogin(), funcionario.getSenha());
    }
}
