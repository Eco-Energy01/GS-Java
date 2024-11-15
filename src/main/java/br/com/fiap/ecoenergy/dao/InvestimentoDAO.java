package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Investimento;

import java.sql.*;

public class InvestimentoDAO {

    public void salvar(Investimento investimento) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_GS_INVESTIMENTO (INTERESSE, EMPRESA, SETOR, TELEFONE, INVESTIMENTO) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, investimento.getAreaInteresse());
            stmt.setString(2, investimento.getEmpresa());
            stmt.setString(3, investimento.getSetor());
            stmt.setString(4, investimento.getTelefone());
            stmt.setDouble(5, investimento.getValorInvestimento());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                investimento.setId(generatedKeys.getString(1));
            }

            System.out.println("Investimento cadastrado com sucesso!");

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
