package br.com.fiap.ecoenergy.model;

public class Endereco {

    // Atributos
    private String cep;
    private String logradouro;
    private int numero;
    private String estado;
    private String cidade;

    // Método de retornar os dados do endereço
    public String retornarEndereco() {
        return "CEP: " + cep + ", Logradouro: " + logradouro + ", N°: " + numero + ", Estado: " + estado + ", Cidade: " + cidade;
    }

    // Construtores
    public Endereco(String cep, String logradouro, int numero, String estado, String cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Endereco(String cep, String logradouro, int numero, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.estado = estado;
    }

    public Endereco(String cep, String logradouro, int numero) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public Endereco(String cep, String logradouro) {
        this.cep = cep;
        this.logradouro = logradouro;
    }

    public Endereco(String cep) {
        this.cep = cep;
    }

    public Endereco() {}

    // Getters e Setters
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
