package br.com.fiap.ecoenergy.model;

public class Cliente {

    // Atributos
    private String id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private Endereco endereco;

    // Método de verificar senha
    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }


    // Construtores
    public Cliente(String id, String nome, String telefone, String cpf, String email, String senha, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Cliente(String id, String nome, String telefone, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(String nome, String telefone, String cpf, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Cliente () {}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return  "ID       - " + id + "\n" +
                "NOME     - " + nome + "\n" +
                "TELEFONE - " + telefone + "\n" +
                "CPF      - " + cpf + "\n" +
                "EMAIL    - " + email + "\n" +
                "SENHA    - " + senha + "\n";

    }
}
