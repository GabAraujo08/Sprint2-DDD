package org.example.mecanica;
import  java.util.Arrays;

import org.example.categoriaservico.CategoriaServico;
import org.example.servico.Servico;

import static java.lang.Long.sum;

public class Mecanica {
    private String nome;
    private String endereco; //Talvez seja um array de string que guarda os atributos de endereço (nome rua, num, cep, cidade...)
    private String telefone;
    private Servico servicos[];
    private Double avaliacaoMedia;
    private Integer notas[];
    private String cnpjMecanica;

    public Mecanica(String nome, String endereco, String telefone, Servico servicos[], String cnpjMecanica) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.servicos = servicos;
        this.cnpjMecanica = cnpjMecanica;
    }
    private Double calcularAvaliacaoMedia(){
        int soma = 0;
        for (int elemento : notas) {
            soma += elemento;
        }
        return this.avaliacaoMedia = (double) soma / notas.length;

    }
    public void alterarCnpj(String cnpj){
        this.cnpjMecanica = cnpj;
    }
    public void alterarEndereco(String endereco){
        this.endereco = endereco;
    }
    public void alterarTelefone(String telefone){
        this.telefone = telefone;
    }
    public void adicionarServico(Servico servico){
        servicos = Arrays.copyOf(servicos, servicos.length + 1);
        servicos[servicos.length - 1] = servico;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Servico[] getServicos() {
        return servicos;
    }

    public Double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public Integer[] getNotas() {
        return notas;
    }

    public String getCnpjMecanica() {
        return cnpjMecanica;
    }

    public void setNotas(Integer[] notas) {
        this.notas = notas;
        calcularAvaliacaoMedia();
    }
}
