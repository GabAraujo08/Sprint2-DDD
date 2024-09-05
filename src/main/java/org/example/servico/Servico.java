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
        if (valor < 0){
            throw new IllegalArgumentException("O valor do serviço não pode ser negativo.");
        }else if(valor.equals(this.valor)){
            throw new IllegalArgumentException("O valor do serviço já está definido como R$" + getValor());
        }
        this.valor = valor;
    }

    public void alterarDescricaoServico(String descricao){
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do serviço não pode ser vazia.");
        }
        this.descricao = descricao;
    }
    public void alterarCategoriaServico(CategoriaServico categoria){
        this.categoria = categoria;
    }

    public void excluirServico(){
        this.nome = null;
        this.descricao = null;
        this.categoria = null;
        this.valor = null;
        this.idServico = null;
    }

}
