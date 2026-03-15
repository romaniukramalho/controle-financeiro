import java.math.BigDecimal;
import java.time.LocalDate;

public class Gasto extends Transactions
{
    public Gasto(int id, BigDecimal valor, String desc, LocalDate data, Categoria categoria)
    {
        super(id, valor, desc, data, categoria);
    }

    @Override
    public TipoTransaction getTipo() {
        return TipoTransaction.GASTO;
    }
}