package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record EmprestimoRequest(
        @NotNull
        @DatePattern
        LocalDate data,
        @NotNull
        Double valor,
        @NotNull
        Integer clienteId,
        @NotEmpty
        List<ItemDeEmprestimoRequest> itemDeEmprestimoRequests
) {
}
