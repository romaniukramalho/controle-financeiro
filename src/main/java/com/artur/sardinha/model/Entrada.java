package com.artur.sardinha.model;

import com.artur.sardinha.enums.TipoTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Entrada extends Transactions
{
    public Entrada(int id, BigDecimal valor, String desc, LocalDate data)
    {
        super(id, valor, desc, data);
    }
    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.ENTRADA;
    }
}
