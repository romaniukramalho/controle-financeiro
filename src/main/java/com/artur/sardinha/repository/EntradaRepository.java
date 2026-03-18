package com.artur.sardinha.repository;

import com.artur.sardinha.service.DatabaseConnection;
import com.artur.sardinha.enums.Categoria;
import com.artur.sardinha.model.Ganhos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntradaRepository
{
    private Connection conn;

    public EntradaRepository()
    {
        this.conn = DatabaseConnection.conectar();
    }

    public void SalvarEntrada(Ganhos ganhos) {
        String sql = "INSERT INTO ganhos (valor, descricao, data, categoria) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, ganhos.getValor());
            ps.setString(2, ganhos.getDesc());
            ps.setDate(3, Date.valueOf(ganhos.getData()));
            ps.setString(4, ganhos.getCategoria().name());

            ps.executeUpdate();
            System.out.println("Entrada salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Não foi possivel salvar a entrada: " + e.getMessage());
        }
    }
    public List<Ganhos> buscarTodos()
    {
        List<Ganhos> ganhos = new ArrayList<>();
        String sql = "SELECT * FROM ganhos";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ganhos ganho = new Ganhos(
                        rs.getInt("id"),
                        rs.getBigDecimal("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate(),
                        Categoria.valueOf(rs.getString("categoria"))
                );
                ganhos.add(ganho);
            }
        } catch (SQLException e) {
            System.out.println("Nao foi possivel buscar os gastos" + e.getMessage());
        }

        return ganhos;
    }
    public void deletar(int id)
    {
        String sql = "DELETE FROM Ganhos WHERE id = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ganho deletado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Nao foi possivel excluir o ganho: "+e.getMessage());
        }
    }
}

