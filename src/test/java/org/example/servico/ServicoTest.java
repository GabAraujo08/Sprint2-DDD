package org.example.servico;

import org.example.categoriaservico.CategoriaServico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    @Test
    void quando_construtor_todos_campos_diferente_null(){
        Servico servico = new Servico("nome", "descricao", CategoriaServico.MECANICA, 10.0, 1);
        Assertions.assertNotNull(servico.getNome());
        Assertions.assertNotNull(servico.getDescricao());
        Assertions.assertNotNull(servico.getCategoria());
        Assertions.assertNotNull(servico.getValor());
        Assertions.assertNotNull(servico.getIdServico());

    }
    @Test
    void quando_excluirServico_todos_campos_null(){
        Servico servico = new Servico("nome", "descricao", CategoriaServico.MECANICA, 10.0, 1);
        servico.excluirServico();
        Assertions.assertNull(servico.getNome());
        Assertions.assertNull(servico.getDescricao());
        Assertions.assertNull(servico.getCategoria());
        Assertions.assertNull(servico.getValor());
        Assertions.assertNull(servico.getIdServico());
    }
    @Test
    void quando_alterarCategoriaServico_para_ELETRICA_getEletrica(){
        Servico servico = new Servico("nome", "descricao", CategoriaServico.MECANICA, 10.0, 1);
        servico.alterarCategoriaServico(CategoriaServico.ELETRICA);
        assertEquals(CategoriaServico.ELETRICA, servico.getCategoria());
    }
    @Test
    void quando_alterarDescricaoServico_para_nova_descricao_getNovaDescricao(){
        Servico servico = new Servico("nome", "descricao", CategoriaServico.MECANICA, 10.0, 1);
        servico.alterarDescricaoServico("nova descricao");
        assertEquals("nova descricao", servico.getDescricao());
    }
    @Test
    void quando_alterarValorServico_para_20_get20(){
        Servico servico = new Servico("nome", "descricao", CategoriaServico.MECANICA, 10.0, 1);
        servico.alterarValorServico(20.0);
        assertEquals(20.0, servico.getValor());
    }

}