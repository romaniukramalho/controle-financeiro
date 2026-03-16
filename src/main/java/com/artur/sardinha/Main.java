package com.artur.sardinha;
import com.artur.sardinha.service.DatabaseConnection;

import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args)
    {
        DatabaseConnection.conectar();

        Gasto gasto = new Gasto(
                1,
                new BigDecimal("50.00"),
                "Almoço",
                LocalDate.now(),
                Categoria.ALIMENTO
        );
        System.out.println(gasto);
    }
}