package org.example.pecas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PecaTest {
    @Test
    void quando_construtor_todos_campos_diferente_null(){
        Peca peca = new Peca("Pneu", "Pneu de carro", 200.0, "Pirelli", 1);
        Assertions.assertNotNull(peca.getNome());
        Assertions.assertNotNull(peca.getDescricao());
        Assertions.assertNotNull(peca.getPreco());
        Assertions.assertNotNull(peca.getMarca());
        Assertions.assertNotNull(peca.getIdPeca());
    }
    @Test
    void quando_exlcuir_peca_todos_campos_null(){
        Peca peca = new Peca("Pneu", "Pneu de carro", 200.0, "Pirelli", 1);
        peca.excluirPeca();
        Assertions.assertNull(peca.getNome());
        Assertions.assertNull(peca.getDescricao());
        Assertions.assertEquals(0, peca.getPreco());
        Assertions.assertNull(peca.getMarca());
        Assertions.assertNull(peca.getIdPeca());
    }

    @Test
    void quando_alterar_nome(){
        Peca peca = new Peca("Pneu", "Pneu de carro", 200.0, "Pirelli", 1);
        peca.alterarNome("Pneu de moto");
        Assertions.assertEquals("Pneu de moto", peca.getNome());
    }
    @Test
    void quando_alterar_descricao(){
        Peca peca = new Peca("Pneu", "Pneu de carro", 200.0, "Pirelli", 1);
        peca.alterarDescricao("Pneu de moto");
        Assertions.assertEquals("Pneu de moto", peca.getDescricao());
    }
    @Test
    void quando_alterar_preco(){
        Peca peca = new Peca("Pneu", "Pneu de carro", 200.0, "Pirelli", 1);
        peca.alterarPreco(150.0);
        Assertions.assertEquals(150.0, peca.getPreco());
    }
}