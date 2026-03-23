package com.artur.sardinha.repository;

import com.artur.sardinha.enums.TipoInvestimento;
import com.artur.sardinha.service.DatabaseConnection;
import com.artur.sardinha.model.Deposito;
import com.artur.sardinha.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoRepository {

    private Connection conn;

    public InvestimentoRepository() {
        this.conn = DatabaseConnection.conectar();
    }

    public void salvarInvestimento(Investimento investimento) {
        try {
            // verifica se já existe
            String sqlBusca = "SELECT id FROM investimentos WHERE nome = ?";
            PreparedStatement psBusca = conn.prepareStatement(sqlBusca);
            psBusca.setString(1, investimento.getDesc());
            ResultSet rs = psBusca.executeQuery();

            int investimentoId;

            if (rs.next()) {
                // já existe — usa o id existente
                investimentoId = rs.getInt("id");
                System.out.println("Investimento já existe, adicionando depósito...");
            } else {
                // não existe — cria novo
                String sqlInsert = "INSERT INTO investimentos (nome, tipo_investimento) VALUES (?, ?) RETURNING id";
                PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
                psInsert.setString(1, investimento.getDesc());
                psInsert.setString(2, investimento.getTipoInvestimento().name());
                ResultSet rsInsert = psInsert.executeQuery();
                rsInsert.next();
                investimentoId = rsInsert.getInt("id");
                System.out.println("Investimento cadastrado com sucesso!");
            }

            // salva o depósito
            String sqlDeposito = "INSERT INTO depositos (investimento_id, valor, data) VALUES (?, ?, ?)";
            PreparedStatement psDeposito = conn.prepareStatement(sqlDeposito);
            psDeposito.setInt(1, investimentoId);
            psDeposito.setBigDecimal(2, investimento.getValor());
            psDeposito.setDate(3, Date.valueOf(investimento.getData()));
            psDeposito.executeUpdate();
            System.out.println("Depósito registrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Não foi possível cadastrar o investimento: " + e.getMessage());
        }
    }

    public List<Investimento> BuscarTodos() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT i.id, i.nome, i.tipo_investimento, COALESCE(SUM(d.valor), 0) as total " +
                "FROM investimentos i LEFT JOIN depositos d ON i.id = d.investimento_id " +
                "GROUP BY i.id, i.nome, i.tipo_investimento";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Investimento investimento = new Investimento(
                        rs.getInt("id"),
                        rs.getBigDecimal("total"),
                        rs.getString("nome"),
                        null,
                        TipoInvestimento.valueOf(rs.getString("tipo_investimento"))
                );
                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar investimentos: " + e.getMessage());
        }
        return investimentos;
    }

    public List<Deposito> buscarDepositos(int investimentoId) {
        List<Deposito> depositos = new ArrayList<>();
        String sql = "SELECT * FROM depositos WHERE investimento_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, investimentoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Deposito deposito = new Deposito(
                        rs.getInt("id"),
                        rs.getInt("investimento_id"),
                        rs.getBigDecimal("valor"),
                        rs.getDate("data").toLocalDate()
                );
                depositos.add(deposito);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar depósitos: " + e.getMessage());
        }
        return depositos;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM investimentos WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Investimento deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Não é possível deletar um investimento que possui depósitos!");
        }
    }
}