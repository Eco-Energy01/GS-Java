package br.com.fiap.ecoenergy.model;

public class PlacaEstado {
    private String nomeEstado;
    private int quantidadePlaca;

    public PlacaEstado(String nomeEstado, int quantidadePlaca) {
        this.nomeEstado = nomeEstado;
        this.quantidadePlaca = quantidadePlaca;
    }
    public PlacaEstado() {}

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
