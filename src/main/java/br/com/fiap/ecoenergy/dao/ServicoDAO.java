package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    // Método de salvar serviços
    public void salvar(Servico servico) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO T_GS_SERVICO (ENDERECO, LOCAL, QUANTIDADE_PLACA, TIPO_SERVICO, TELEFONE) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, servico.getEndereco());
            stmt.setString(2, servico.getLocal());
            stmt.setInt(3, servico.getQuantidadePlaca());
            stmt.setString(4, servico.getTipoServico());
            stmt.setString(5, servico.getTelefone());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                servico.setId(generatedKeys.getString(1));
            }

            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar serviço");
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

    // Método de listar serviços
    public List<Servico> listar() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM T_GS_SERVICO";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getString("id_servico"));
                servico.setEndereco(rs.getString("endereco"));
                servico.setLocal(rs.getString("local"));
                servico.setQuantidadePlaca(rs.getInt("quantidade_placa"));
                servico.setTipoServico(rs.getString("tipo_servico"));
                servico.setTelefone(rs.getString("telefone"));
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    // Método de pesquisar por ID
    public Servico pesquisarPorId(String id) {
        Servico servico = null;
        String sql = "SELECT * FROM T_GS_SERVICO WHERE id_servico = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servico = new Servico(
                        rs.getString("id_servico"),
                        rs.getString("endereco"),
                        rs.getString("local"),
                        rs.getInt("quantidade_placa"),
                        rs.getString("tipo_servico"),
                        rs.getString("telefone")
                );
            } else {
                throw new IdNaoEncontradoException("Serviço não encontrado com ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao pesquisar serviço", e);
        }

        return servico;
    }

    // Método de atualizar serviço
    public void atualizar(Servico servico) {
        String sql = "UPDATE T_GS_SERVICO SET endereco = ?, local = ?, quantidade_placa = ?, tipo_servico = ?, telefone = ? WHERE id_servico = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, servico.getEndereco());
            stmt.setString(2, servico.getLocal());
            stmt.setInt(3, servico.getQuantidadePlaca());
            stmt.setString(4, servico.getTipoServico());
            stmt.setString(5, servico.getTelefone());
            stmt.setString(6, servico.getId());

            System.out.println("Executando atualização para o serviço: " + servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método de remover serviço
    public void remover(String id) {
        String sql = "DELETE FROM T_GS_SERVICO WHERE id_servico = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
