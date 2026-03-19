package com.artur.sardinha.repository;

import com.artur.sardinha.service.DatabaseConnection;
import com.artur.sardinha.model.Entrada;

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

    public void SalvarEntrada(Entrada entradas) {
        String sql = "INSERT INTO entradas (valor, descricao, data) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, entradas.getValor());
            ps.setString(2, entradas.getDesc());
            ps.setDate(3, Date.valueOf(entradas.getData()));

            ps.executeUpdate();
            System.out.println("Entrada salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Não foi possivel salvar a entrada: " + e.getMessage());
        }
    }
    public List<Entrada> buscarTodos()
    {
        List<Entrada> entradas = new ArrayList<>();
        String sql = "SELECT * FROM entradas";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entrada ganho = new Entrada(
                        rs.getInt("id"),
                        rs.getBigDecimal("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate()
                );
                entradas.add(ganho);
            }
        } catch (SQLException e) {
            System.out.println("Nao foi possivel buscar os gastos" + e.getMessage());
        }

        return entradas;
    }
    public void deletar(int id)
    {
        String sql = "DELETE FROM entradas WHERE id = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Entrada deletado com sucesso!");
        }
        catch(SQLException e)
        {
            System.out.println("Nao foi possivel excluir a entrada: "+e.getMessage());
        }
    }
}

