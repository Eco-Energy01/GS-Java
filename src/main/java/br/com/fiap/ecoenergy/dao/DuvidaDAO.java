package br.com.fiap.ecoenergy.dao;

import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.factory.ConnectionFactory;
import br.com.fiap.ecoenergy.model.Cliente;
import br.com.fiap.ecoenergy.model.Duvida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Método de listar duvidas
    public List<Duvida> listar() {
        List<Duvida> duvidas = new ArrayList<>();
        String sql = "SELECT * FROM T_GS_DUVIDA";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Duvida duvida = new Duvida();
                duvida.setId(rs.getString("id_duvida"));
                duvida.setAssunto(rs.getString("assunto"));
                duvida.setMensagem(rs.getString("mensagem"));
                String idCliente = rs.getString("id_cliente");
                Cliente cliente = new ClienteDAO().pesquisarPorId(idCliente);

                duvida.setCliente(cliente);
                duvidas.add(duvida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duvidas;
    }

    // Método de pesquisar por ID
    public Duvida pesquisarPorId(String id) {
        Duvida duvida = null;
        String sql = "SELECT * FROM T_GS_DUVIDA WHERE id_duvida = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String idCliente = rs.getString("id_cliente");
                Cliente cliente = new ClienteDAO().pesquisarPorId(idCliente);

                duvida = new Duvida(
                        rs.getString("id_duvida"),
                        rs.getString("assunto"),
                        rs.getString("mensagem"),
                        cliente
                );
            } else {
                throw new IdNaoEncontradoException("Duvida não encontrado com ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao pesquisar duvida", e);
        }

        return duvida;
    }

    // Método de atualizar duvida
    public void atualizar(Duvida duvida) {
        String sql = "UPDATE T_GS_DUVIDA SET assunto = ?, mensagem = ? WHERE id_duvida = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, duvida.getAssunto());
            stmt.setString(2, duvida.getMensagem());
            stmt.setString(3, duvida.getId());

            System.out.println("Executando atualização para a duvida: " + duvida.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar duvida: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método de remover duvida
    public void remover(String id) {
        String sql = "DELETE FROM T_GS_DUVIDA WHERE id_duvida = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
