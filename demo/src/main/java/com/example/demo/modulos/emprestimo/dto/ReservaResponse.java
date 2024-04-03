package com.example.demo.modulos.emprestimo.dto;

import com.example.demo.modulos.comum.annotations.DatePattern;
import com.example.demo.modulos.emprestimo.model.Reserva;

import java.time.LocalDate;

public record ReservaResponse(
        Integer id,
        @DatePattern
        LocalDate data,
        Integer status,
        String clienteNome,
        Integer livroFisicoId,
        String livroTitulo
) {
    public static ReservaResponse of(Reserva reserva) {
        return new ReservaResponse(reserva.getId(), reserva.getData(),
                reserva.getStatus(), reserva.getCliente().getNome(), reserva.getLivroFisico().getId(),
                reserva.getLivroFisico().getLivro().getTitulo());
    }
}