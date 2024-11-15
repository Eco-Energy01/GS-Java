package br.com.fiap.ecoenergy.model;

public class Investimento {

    // Atributos
    private String id;
    private String areaInteresse;
    private String empresa;
    private String setor;
    private String telefone;
    private double valorInvestimento;

    // Construtores
    public Investimento(String id, String areaInteresse, String empresa, String setor, String telefone, double valorInvestimento) {
        this.id = id;
        this.areaInteresse = areaInteresse;
        this.empresa = empresa;
        this.setor = setor;
        this.telefone = telefone;
        this.valorInvestimento = valorInvestimento;
    }
    public Investimento(String areaInteresse, String empresa, String setor, String telefone, double valorInvestimento) {
        this.areaInteresse = areaInteresse;
        this.empresa = empresa;
        this.setor = setor;
        this.telefone = telefone;
        this.valorInvestimento = valorInvestimento;
    }
    public Investimento() {}

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    @Override
    public String toString() {
        return  "ID                   - " + id + "\n" +
                "ÁREA INTERESSE       - " + areaInteresse + "\n" +
                "EMPRESA              - " + empresa + "\n" +
                "SETOR                - " + setor + "\n" +
                "VALOR INVESTIMENTO   - " + valorInvestimento + "\n" +
                "TELEFONE             - " + telefone + "\n";
    }
}
