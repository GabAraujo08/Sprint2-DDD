package org.example.servico;

import org.example.categoriaservico.CategoriaServico;

public class Servico {
    private String nome;
    private String descricao;
    private CategoriaServico categoria;
    private Double valor;
    private Integer idServico;

    public Servico(String nome, String descricao, CategoriaServico categoria, Double valor, Integer idServico) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaServico getCategoria() {
        return categoria;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void alterarValorServico(Double valor){
        this.valor = valor;
    }

    public void alterarDescricaoServico(String descricao){
        this.descricao = descricao;
    }
    public void alterarCategoriaServico(CategoriaServico categoria){
        this.categoria = categoria;
    }

}
