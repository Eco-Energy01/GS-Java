package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDAO {

    // Método de salvar investimento
    public void salvar(Investimento investimento) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_GS_INVESTIMENTO (AREA_DE_INTERESSE, EMPRESA, SETOR, TELEFONE, VALOR_INVESTIMENTO) VALUES (?, ?, ?, ?, ?)";
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

    // Método de listar investimentos
    public List<Investimento> listar() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM T_GS_INVESTIMENTO";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getString("ID_INVESTIMENTO"));
                investimento.setAreaInteresse(rs.getString("AREA_DE_INTERESSE"));
                investimento.setEmpresa(rs.getString("EMPRESA"));
                investimento.setSetor(rs.getString("SETOR"));
                investimento.setTelefone(rs.getString("TELEFONE"));
                investimento.setValorInvestimento(rs.getDouble("VALOR_INVESTIMENTO"));
                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return investimentos;
    }

    // Método de pesquisar por ID
    public Investimento pesquisarPorId(String id) {
        Investimento investimento = null;
        String sql = "SELECT * FROM T_GS_INVESTIMENTO WHERE id_investimento = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                investimento = new Investimento(
                        rs.getString("ID_INVESTIMENTO"),
                        rs.getString("AREA_DE_INTERESSE"),
                        rs.getString("EMPRESA"),
                        rs.getString("SETOR"),
                        rs.getString("TELEFONE"),
                        rs.getDouble("VALOR_INVESTIMENTO")
                );
            } else {
                throw new IdNaoEncontradoException("Investimento não encontrado com ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao pesquisar investimento", e);
        }

        return investimento;
    }

    // Método de atualizar investimento
    public void atualizar(Investimento investimento) {
        String sql = "UPDATE T_GS_INVESTIMENTO SET interesse = ?, empresa = ?, setor = ?, telefone = ?, investimento = ? WHERE id_investimento = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, investimento.getAreaInteresse());
            stmt.setString(2, investimento.getEmpresa());
            stmt.setString(3, investimento.getSetor());
            stmt.setString(4, investimento.getTelefone());
            stmt.setDouble(5, investimento.getValorInvestimento());
            stmt.setString(6, investimento.getId());

            System.out.println("Executando atualização para o investimento: " + investimento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar investimento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método de remover investimento
    public void remover(String id) {
        String sql = "DELETE FROM T_GS_INVESTIMENTO WHERE id_investimento = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
