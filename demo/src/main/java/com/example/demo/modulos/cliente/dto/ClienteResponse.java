package com.example.demo.modulos.cliente.dto;

import com.example.demo.modulos.cliente.model.Cliente;
import com.example.demo.modulos.comum.annotations.DatePattern;

import java.time.LocalDate;

public record ClienteResponse(
        Integer id,
        String nome,
        String cpf,
        @DatePattern
        LocalDate dataNascimento,
        Double debito,
        String telefone
) {

    public static ClienteResponse of(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(),
                cliente.getDataNascimento(), cliente.getDebito(), cliente.getTelefone());
    }
}
