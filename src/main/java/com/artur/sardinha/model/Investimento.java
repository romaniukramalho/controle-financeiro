package com.artur.sardinha.model;

import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.enums.TipoTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento extends Transactions
{
    private TipoInvestimento tipoInvestimento;
    private LocalDate vencimento;
    public Investimento(int id, BigDecimal valor, String desc, LocalDate data, TipoInvestimento tipoInvestimento)
    {
        super(id, valor, desc, data);

        this.tipoInvestimento = tipoInvestimento;
    }
    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.INVESTIMENTO;
    }
    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Descrição: " + desc +
                " | Valor: R$ " + valor +
                " | Tipo: " + tipoInvestimento +
                " | Data: " + data;
    }
}
