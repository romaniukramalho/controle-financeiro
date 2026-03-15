import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Transactions
{
    protected int id;
    protected BigDecimal valor;
    protected String desc;
    protected LocalDate data;
    protected Categoria categoria;

    public Transactions(int id, BigDecimal valor, String desc, LocalDate data, Categoria categoria)
    {
        this.id = id;
        this.valor = valor;
        this.desc = desc;
        this.data = data;
        this.categoria = categoria;

    }
    public abstract TipoTransaction getTipo();

    public int getId()
    {
        return id;
    }
    public BigDecimal getValor()
    {
        return valor;
    }
    @Override
    public String toString()
    {
        return "ID: " + id +
                " | Descrição: " + desc +
                " | Valor: R$ " + valor +
                " | Categoria: " + categoria +
                " | Data: " + data;
    }
    public Categoria getCategoria()
    {
        return categoria;
    }
    public String getDesc()
    {
        return desc;
    }
}