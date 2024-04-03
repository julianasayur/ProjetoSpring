package com.example.demo.modulos.emprestimo.dto;

import jakarta.validation.constraints.NotNull;

public record MultaRequest(
        @NotNull
        Double valor,
        boolean pago,
        @NotNull
        Integer itemDeEmprestimoId
) {
}
