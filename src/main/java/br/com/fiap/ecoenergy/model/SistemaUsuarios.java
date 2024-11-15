package br.com.fiap.ecoenergy.model;

import br.com.fiap.ecoenergy.dao.ClienteDAO;

import java.sql.SQLException;

public class SistemaUsuarios {

    private ClienteDAO clienteDAO;

    // Construtor
    public SistemaUsuarios() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método de cadastrar usuário
    public boolean cadastrarUsuario(String nome, String telefone, String cpf, String email, String senha) {
        cpf = cpf.trim();
        email = email.trim();
        telefone = telefone.trim();
        nome = nome.trim();

        if (clienteDAO.clienteExiste(telefone, cpf, email)) {
            return false;
        }
        Cliente novoCliente = new Cliente(nome, telefone, cpf, email, senha);
        clienteDAO.cadastrar(novoCliente);

        return true;
    }

    // Método de login usuário
    public Cliente loginUsuario(String email, String senha) throws SQLException {
        email = email.trim();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteBanco = clienteDAO.buscarPorLogin(email, senha);

        if (clienteBanco != null && clienteBanco.verificarSenha(senha)) {
            return clienteBanco;
        }

        return null;
    }

}
