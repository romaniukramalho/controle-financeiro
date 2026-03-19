package com.artur.sardinha.model;

import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.enums.TipoTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento extends Transactions
{
    private TipoInvestimento tipoInvestimento;
    private BigDecimal rentabilidade;
    private LocalDate vencimento;
    public Investimento(int id, BigDecimal valor, String desc, LocalDate data, TipoInvestimento tipoInvestimento, BigDecimal rentabilidade)
    {
        super(id, valor, desc, data);

        this.tipoInvestimento = tipoInvestimento;
        this.rentabilidade = rentabilidade;
    }
    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.INVESTIMENTO;
    }
    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    public BigDecimal getRentabilidade()
    {
        return rentabilidade;
    }
    @Override
    public String toString()
    {
        return "ID: " + id +
                " | Tipo: " + getTipo() +
                " | Descrição: " + desc +
                " | Valor: R$ " + valor +
                " | Data: " + data +
                " | Rentabilidade: " + rentabilidade;
    }
}
