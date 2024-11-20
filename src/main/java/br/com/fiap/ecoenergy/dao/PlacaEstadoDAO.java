package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.PlacaEstado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlacaEstadoDAO {
    public PlacaEstado buscarPorEstado(String nomeEstado) throws SQLException {
        PlacaEstado placaEstado = null;
        String sql = "SELECT * FROM T_GS_PLACA_ESTADO WHERE NOME_ESTADO = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeEstado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                placaEstado = new PlacaEstado(
                        rs.getString("NOME_ESTADO"),
                        rs.getInt("QUANTIDADE_PLACA")
                );
            } else {
                throw new SQLException("Email não encontrado ou senha incorreta.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar estado: " + e.getMessage());
            throw e;
        }

        return placaEstado;
    }
}
