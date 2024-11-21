package br.com.fiap.ecoenergy.model;

public class Servico {

    // Atributos
    private String id;
    private String endereco;
    private String local;
    private int quantidadePlaca;
    private String tipoServico;
    private String telefone;

    // Construtores
    public Servico(String id, String endereco, String local, int quantidadePlaca, String tipoServico, String telefone) {
        this.id = id;
        this.endereco = endereco;
        this.local = local;
        this.quantidadePlaca = quantidadePlaca;
        this.tipoServico = tipoServico;
        this.telefone = telefone;
    }
    public Servico(String endereco, String local, int quantidadePlaca, String tipoServico, String telefone) {
        this.endereco = endereco;
        this.local = local;
        this.quantidadePlaca = quantidadePlaca;
        this.tipoServico = tipoServico;
        this.telefone = telefone;
    }
    public Servico() {}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getQuantidadePlaca() {
        return quantidadePlaca;
    }

    public void setQuantidadePlaca(int quantidadePlaca) {
        this.quantidadePlaca = quantidadePlaca;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return  "ID                     - " + id + "\n" +
                "ENDEREÇO               - " + endereco + "\n" +
                "LOCAL                  - " + local + "\n" +
                "QUANTIDADE PLACA       - " + quantidadePlaca + "\n" +
                "TIPO SERVIÇO           - " + tipoServico + "\n" +
                "TELEFONE               - " + telefone + "\n";
    }

}
