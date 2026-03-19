package com.artur.sardinha.repository;

import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.service.DatabaseConnection;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoRepository
{
    private Connection conn;

    public InvestimentoRepository()
    {
        this.conn = DatabaseConnection.conectar();
    }

    public void SalvarInvestimento(Investimento investimento)
    {
        String sql = "INSERT INTO investimentos (valor, descricao, data, tipo_investimento, rentabilidade) VALUES (?, ? ,?, ? ,?)";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, investimento.getValor());
            ps.setString(2, investimento.getDesc());
            ps.setDate(3, Date.valueOf(investimento.getData()));
            ps.setString(4, investimento.getCategoria().name());
            ps.setString(5, investimento.getTipoInvestimento().name());
            ps.setBigDecimal(6, investimento.getRentabilidade());

            ps.executeUpdate();
            System.out.println("Investimento cadastrado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Não foi possível cadastrar o investimento: "+e.getMessage());
        }
    }
    public List<Investimento> BuscarTodos()
    {
        List<Investimento> investimentos = new ArrayList<>();

        String sql = "SELECT * FROM investimentos";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Investimento investimento = new Investimento(
                        rs.getInt("id"),
                        rs.getBigDecimal("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate(),
                        TipoInvestimento.valueOf(rs.getString("tipo_investimento")),
                        rs.getBigDecimal("rentabilidade")
                );
                investimentos.add(investimento);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao buscar investimentos: " + e.getMessage());
        }
        return investimentos;
    }

    public void deletar(int id)
    {
        String sql = "DELETE FROM Ganhos WHERE id = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Investimento deletado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Nao foi possivel excluir o investimento: "+e.getMessage());
        }
    }
}
