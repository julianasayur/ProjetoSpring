package com.example.demo.modulos.comum.util;

import java.time.LocalDate;

public class DateUtil {

    public static void validarAnteriorAoPeriodo(LocalDate data, LocalDate perido, String message) {
        if (data.isAfter(perido)) {
            throw new IllegalArgumentException(message);
        }
    }
}
