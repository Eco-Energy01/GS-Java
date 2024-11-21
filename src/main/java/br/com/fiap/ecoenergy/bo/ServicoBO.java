package br.com.fiap.ecoenergy.bo;

import br.com.fiap.ecoenergy.model.Servico;

public class ServicoBO {
    public void validarServico(Servico servico) {
        if (servico.getEndereco() == null || servico.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("Endereço é obrigatório.");
        }
        if (servico.getLocal() == null || servico.getLocal().isEmpty()) {
            throw new IllegalArgumentException("Local é obrigatório.");
        }
//        if (servico.getQuantidadePlaca() == null || servico.getQuantidadePlaca() <= 0) {
//            throw new IllegalArgumentException("Quantidade de placa é obrigatória e deve ser maior que zero.");
//        }
        if (servico.getTipoServico() == null || servico.getTipoServico().isEmpty()) {
            throw new IllegalArgumentException("Tipo do serviço é obrigatório.");
        }
        if (servico.getTelefone() == null || servico.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Telefone de contato é obrigatório.");
        }
    }
}
