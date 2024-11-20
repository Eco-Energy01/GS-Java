package br.com.fiap.ecoenergy.model;

public class Duvida {

    private String id;
    private String assunto;
    private String mensagem;
    private Cliente cliente;

    public Duvida(String id, String assunto, String mensagem, Cliente cliente) {
        this.id = id;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.cliente = cliente;
    }

    public Duvida(String assunto, String mensagem, Cliente cliente) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.cliente = cliente;
    }

    public Duvida() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
