package br.com.fiap.ecoenergy.bo;

import br.com.fiap.ecoenergy.model.Cliente;
import java.util.regex.Pattern;

public class ClienteBO {

    // Valida os dados obrigatórios de um cliente antes de cadastrar ou atualizar
    public void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O telefone é obrigatório.");
        }

        if (cliente.getCpf() == null || !validarCPF(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (cliente.getEmail() == null || !validarEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
    }

    // Método para validar CPF (exemplo simplificado, você pode implementar uma lógica mais completa)
    public boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}"); // Verifica se o CPF tem 11 dígitos
    }

    // Método para validar o formato de um email
    public boolean validarEmail(String email) {
        // Expressão regular básica para verificar formato de email
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    // Método para atualizar os dados de um cliente
    public void atualizarCliente(Cliente cliente, Cliente novosDados) {
        if (novosDados.getNome() != null && !novosDados.getNome().isEmpty()) {
            cliente.setNome(novosDados.getNome());
        }

        if (novosDados.getTelefone() != null && !novosDados.getTelefone().isEmpty()) {
            cliente.setTelefone(novosDados.getTelefone());
        }

        if (novosDados.getEmail() != null && validarEmail(novosDados.getEmail())) {
            cliente.setEmail(novosDados.getEmail());
        }

        if (novosDados.getEndereco() != null) {
            cliente.setEndereco(novosDados.getEndereco());
        }
    }

    // Método para alterar a senha do cliente
    public boolean alterarSenha(Cliente cliente, String senhaAtual, String novaSenha) {
        if (cliente.verificarSenha(senhaAtual)) {
            if (novaSenha != null && !novaSenha.isEmpty()) {
                cliente.setSenha(novaSenha);
                return true;
            } else {
                throw new IllegalArgumentException("A nova senha não pode ser vazia.");
            }
        } else {
            throw new IllegalArgumentException("A senha atual está incorreta.");
        }
    }
}
