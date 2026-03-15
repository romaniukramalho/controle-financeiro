package com.artur.sardinha.model;

import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.enums.TipoTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento extends Transactions
{
    private TipoInvestimento tipoInvestimento;

    public Investimento(int id, BigDecimal valor, String desc, LocalDate data, Categoria categoria, TipoInvestimento tipoInvestimento)
    {
        super(id, valor, desc, data, categoria);

        this.tipoInvestimento = tipoInvestimento;
    }
    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.INVESTIMENTO;
    }
    @Override
    public String toString()
    {
        return "ID: " + id +
                " | Tipo: " + getTipo() +
                " | Descrição: " + desc +
                " | Valor: R$ " + valor +
                " | com.artur.sardinha.enums.Categoria: " + categoria +
                " | Data: " + data;
    }
}
