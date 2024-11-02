package org.example.entities.orcamento;

import org.example.entities.categoriaservico.CategoriaServico;
import org.example.entities.mecanica.Mecanica;
import org.example.entities.orcamento.Orcamento;
import org.example.entities.pecas.Peca;
import org.example.entities.servico.Servico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrcamentoTest {
    Peca peca = new Peca("nome", "descricao", 20.0, "xpto", 0);
    Peca pecaLista[] = {peca};
    Servico servico = new Servico("nome", "descricao", "Elétrica", 20.0, 0l);
    Servico servico1 = new Servico("nome", "descricao", "Elétrica", 20.0, 0l);
    Servico servicoLista[] = {servico, servico1};
    Mecanica mecanica = new Mecanica(1l, "nome", "endereco", "telefone", "cnpjMecanica");
    @Test
    void quando_construtor_chamado_todos_campos_diferente_null(){
        Orcamento orcamento = new Orcamento(mecanica, servicoLista, pecaLista, 1);
        Assertions.assertNotNull(orcamento.getIdOrcamento());
        Assertions.assertNotNull(orcamento.getMecanica());
        Assertions.assertNotNull(orcamento.getPecas());
        Assertions.assertNotNull(orcamento.getServicos());
        Assertions.assertNotNull(orcamento.getValor());

    }

    @Test
    void quando_duas_pecas_de_20_e_um_servico_de_20_valorOrcamento_deve_ser_60(){
        Orcamento orcamento = new Orcamento(mecanica, servicoLista, pecaLista, 1);
        assertEquals(60.0, orcamento.getValor());
    }
}