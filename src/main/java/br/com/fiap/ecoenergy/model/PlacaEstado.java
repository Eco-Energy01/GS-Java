package br.com.fiap.ecoenergy.model;

public class PlacaEstado {

    // Atributos
    private String nomeEstado;
    private int quantidadePlaca;

    // Construtores
    public PlacaEstado(String nomeEstado, int quantidadePlaca) {
        this.nomeEstado = nomeEstado;
        this.quantidadePlaca = quantidadePlaca;
    }
    public PlacaEstado() {}

    // Getters e Setters
    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public int getQuantidadePlaca() {
        return quantidadePlaca;
    }

    public void setQuantidadePlaca(int quantidadePlaca) {
        this.quantidadePlaca = quantidadePlaca;
    }
}
