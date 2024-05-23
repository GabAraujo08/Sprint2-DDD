package org.example.orcamento;

import org.example.mecanica.Mecanica;
import org.example.pecas.Peca;
import org.example.servico.Servico;

public class Orcamento {
    private Mecanica mecanica;
    private Servico[] servicos;
    private Peca[] pecas;
    private Double valor;
    private Integer idOrcamento;

    public Orcamento(Mecanica mecanica, Servico servicos[], Peca pecas[], Integer idOrcamento) {
        this.mecanica = mecanica;
        this.servicos = servicos;
        this.pecas = pecas;
        calculaValor();
        this.idOrcamento = idOrcamento;
    }

     private Double calculaValor() {
         Double valor = 0.0;
         for (Peca peca : pecas) {
             valor += peca.getPreco();
         }
         for (Servico servico : servicos) {
             valor += servico.getValor();
         }
         return this.valor = valor;
     }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public Servico[] getServicos() {
        return servicos;
    }

    public Peca[] getPecas() {
        return pecas;
    }

    public Integer getIdOrcamento() {
        return idOrcamento;
    }

    public Double getValor() {
        return valor;
    }
}
