package br.com.fiap.ecoenergy.bo;

import br.com.fiap.ecoenergy.model.Investimento;

public class InvestimentoBO {
    public void validarInvestimento(Investimento investimento) {
        if (investimento.getAreaInteresse() == null || investimento.getAreaInteresse().isEmpty()) {
            throw new IllegalArgumentException("Área de interesse é obrigatória.");
        }
        if (investimento.getEmpresa() == null || investimento.getEmpresa().isEmpty()) {
            throw new IllegalArgumentException("Nome da empresa é obrigatório.");
        }
        if (investimento.getSetor() == null || investimento.getSetor().isEmpty()) {
            throw new IllegalArgumentException("Setor do investimento é obrigatório.");
        }
        if (investimento.getTelefone() == null || investimento.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Telefone de contato é obrigatório.");
        }
        if (investimento.getValorInvestimento() <= 0) {
            throw new IllegalArgumentException("Valor do investimento deve ser maior que zero.");
        }
    }
}
