package com.artur.sardinha.repository;

import com.artur.sardinha.model.Gasto;
import com.artur.sardinha.service.DatabaseConnection;
import com.artur.sardinha.enums.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GastoRepository
{
    private Connection conn;

    public GastoRepository() {
        this.conn = DatabaseConnection.conectar();
    }
    public void salvar(Gasto gasto) {
        String sql = "INSERT INTO gastos (valor, descricao, data, categoria) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, gasto.getValor());
            ps.setString(2, gasto.getDesc());
            ps.setDate(3, Date.valueOf(gasto.getData().toLocalDate()));
            ps.setString(4, gasto.getCategoria().name());

            ps.executeUpdate();
            System.out.println("Gasto salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar gasto: " + e.getMessage());
        }
    }

    public List<Gasto> buscarTodos() {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM gastos";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Gasto gasto = new Gasto(
                        rs.getInt("id"),
                        rs.getBigDecimal("valor"),
                        rs.getString("descricao"),
                        rs.getDate("data").toLocalDate(),
                        Categoria.valueOf(rs.getString("categoria"))
                );
                gastos.add(gasto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar gastos: " + e.getMessage());
        }

        return gastos;
    }
    public void deletar(int id) {
        String sql = "DELETE FROM gastos WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Gasto deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar gasto: " + e.getMessage());
        }
    }
}
