package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Duvida;

import java.sql.*;

public class DuvidaDAO {

    // Método de salvar duvida
    public void salvar(Duvida duvida) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_GS_DUVIDA (ASSUNTO, MENSAGEM, ID_CLIENTE) VALUES (?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, duvida.getAssunto());
            stmt.setString(2, duvida.getMensagem());
            stmt.setString(3, duvida.getCliente().getId());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                duvida.setId(generatedKeys.getString(1));
            }

            System.out.println("Dúvida cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar dúvida");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
