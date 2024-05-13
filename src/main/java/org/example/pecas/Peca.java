package org.example.pecas;

public class Peca {
    private String nome;
    private String descricao;
    private double preco;
    private String marca;
    private Integer idPeca;

    public Peca(String nome, String descricao, double preco, String marca, Integer idPeca) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.marca = marca;
        this.idPeca = idPeca;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getIdPeca() {
        return idPeca;
    }
    public void alterarNome(String nome){
        this.nome = nome;
    }
    public void alterarDescricao(String descricao){
        this.descricao = descricao;
    }
    public void alterarPreco(double preco){
        //TODO: Criar validações para inserções de valores, não permitir valores negativos, não permitir caracteres de texto, etc... Usar try catch e colocar um lançamento de exception :)
        this.preco = preco;
    }
    public void excluirPeca(){
        this.nome = null;
        this.descricao = null;
        this.preco = 0;
        this.marca = null;
        this.idPeca = null;
    }


}
