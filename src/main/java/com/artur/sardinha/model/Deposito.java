package com.artur.sardinha.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deposito {

    private int id;
    private int investimentoId;
    private BigDecimal valor;
    private LocalDate data;

    public Deposito(int id, int investimentoId, BigDecimal valor, LocalDate data) {
        this.id = id;
        this.investimentoId = investimentoId;
        this.valor = valor;
        this.data = data;
    }

    public int getId() { return id; }
    public int getInvestimentoId() { return investimentoId; }
    public BigDecimal getValor() { return valor; }
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        return "Depósito ID: " + id +
                " | Valor: R$ " + valor +
                " | Data: " + data;
    }
}