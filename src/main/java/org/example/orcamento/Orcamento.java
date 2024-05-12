package org.example.orcamento;

import org.example.mecanica.Mecanica;
import org.example.pecas.Peca;
import org.example.servico.Servico;

public class Orcamento {
    private Mecanica mecanica;
    private Servico servicos[];
    private Peca pecas[];
    private Double valor;
    private Integer idOrcamento;

    public Orcamento(Mecanica mecanica, Servico servicos[], Peca pecas[], Double valor, Integer idOrcamento) {
        this.mecanica = mecanica;
        this.servicos = servicos;
        this.pecas = pecas;
        this.valor = valor;
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
         return valor;
     }



}
