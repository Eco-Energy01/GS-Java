package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Servico;

import java.sql.*;

public class ServicoDAO {

    public void salvar(Servico servico) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_GS_SERVICO (LOCAL_SERVICO, TIPO, TIPO_LOCAL, DETALHES_SERVICO, TELEFONE) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, servico.getLocalServico());
            stmt.setString(2, servico.getTipo());
            stmt.setString(3, servico.getTipoLocal());
            stmt.setString(4, servico.getDetalhesServico());
            stmt.setString(5, servico.getTelefone());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                servico.setId(generatedKeys.getString(1));
            }

            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar investimento");
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
