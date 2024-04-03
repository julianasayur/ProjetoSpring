package com.example.demo.modulos.funcionario.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.funcionario.model.Gerente;

import java.time.LocalDate;

public record GerenteResponse(
        Integer id,
        String nome,
        String cpf,
        @DatePattern
        LocalDate dataNascimento,
        String login,
        String senha,
        int numFuncGerenciados
) {
    public static GerenteResponse of(Gerente gerente) {
        return new GerenteResponse(gerente.getId(), gerente.getNome(), gerente.getCpf(),
                gerente.getDataNascimento(), gerente.getLogin(), gerente.getSenha(),
                gerente.getNumFuncGerenciados());
    }
}