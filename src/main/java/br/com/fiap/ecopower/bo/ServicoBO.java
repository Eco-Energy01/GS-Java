package br.com.fiap.ecopower.bo;

import br.com.fiap.ecopower.model.Servico;

public class ServicoBO {
    public void validarServico(Servico servico) {
        if (servico.getLocalServico() == null || servico.getLocalServico().isEmpty()) {
            throw new IllegalArgumentException("Local do serviço é obrigatório.");
        }
        if (servico.getTipo() == null || servico.getTipo().isEmpty()) {
            throw new IllegalArgumentException("Tipo de serviço é obrigatório.");
        }
        if (servico.getTipoLocal() == null || servico.getTipoLocal().isEmpty()) {
            throw new IllegalArgumentException("Tipo do local do serviço é obrigatório.");
        }
        if (servico.getTelefone() == null || servico.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Telefone de contato é obrigatório.");
        }
    }
}
