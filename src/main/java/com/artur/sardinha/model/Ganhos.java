package com.artur.sardinha.model;

import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.enums.TipoTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Ganhos extends Transactions
{
    public Ganhos(int id, BigDecimal valor, String desc, LocalDate data)
    {
        super(id, valor, desc, data);
    }
    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.ENTRADA;
    }
}
