package org.example.entities.servico;

import org.example.entities.categoriaservico.CategoriaServico;

public class Servico {
    private String nome;
    private String descricao;
    private String categoria;
    private Double valor;
    private Long id;

    public Servico(String nome, String descricao, String categoria, Double valor, Long id) {

        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getValor() {
        return valor;
    }

    public Long getIdServico() {
        return id;
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
    public void alterarCategoriaServico(String categoria){
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria do serviço não pode ser vazia.");
        }
        this.categoria = categoria;
    }

    public void excluirServico(){
        this.nome = null;
        this.descricao = null;
        this.categoria = null;
        this.valor = null;
        this.id = null;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }
}
