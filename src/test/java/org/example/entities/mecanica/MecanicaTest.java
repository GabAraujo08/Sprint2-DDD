package org.example.entities.mecanica;

import org.example.entities.categoriaservico.CategoriaServico;
import org.example.entities.mecanica.Mecanica;
import org.example.entities.servico.Servico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MecanicaTest {
    Servico servico = new Servico("Troca de óleo", "Troca de óleo do motor", "Elétrica", 100.00, 1l);
    Servico listaServicos[] = {servico};
    @Test
    void quando_construtor_executado_todos_campos_diferente_null(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        Assertions.assertNotNull(mecanica.getNome());
        Assertions.assertNotNull(mecanica.getEndereco());
        Assertions.assertNotNull(mecanica.getTelefone());
        Assertions.assertNotNull(mecanica.getCnpjMecanica());
        Assertions.assertNotNull(mecanica.getServicos());

    }

    @Test
    void quando_alterarCnpj_para_987654321_getCnpj_987654321(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        mecanica.alterarCnpj("987654321");
        assertEquals("987654321", mecanica.getCnpjMecanica());
    }
//    @Test
//    void quando_adiciona_servico_todas_informacoes_iguais_ao_servico_adicionado(){
//        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
//        Servico servico1 = new Servico("Reforma de parachoque", "Reforma de parachoque", "Elétrica", 100.00, 1l);
//        mecanica.adicionarServico(servico1);
//        Servico servicos[] = mecanica.getServicos();
//        Servico[] servicoMecanica = mecanica.getServicos();
//        Assertions.assertEquals("Reforma de parachoque", servicoMecanica[1].getNome());
//        Assertions.assertEquals("Reforma de parachoque", servicoMecanica[1].getDescricao());
//        Assertions.assertEquals(CategoriaServico.REFORMA, servicoMecanica[1].getCategoria());
//        Assertions.assertEquals(100.00, servicoMecanica[1].getValor());
//        Assertions.assertEquals(1, servicoMecanica[1].getIdServico());
//    }

    @Test
    void quando_alterar_telefone_para_987654321_getTelefone_987654321(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        mecanica.alterarTelefone("987654321");
        assertEquals("987654321", mecanica.getTelefone());
    }
    @Test
    void quando_alterar_endereco_para_Rua_do_Joao_getEndereco_Rua_do_Joao(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        mecanica.alterarEndereco("Rua do João, 123");
        assertEquals("Rua do João, 123", mecanica.getEndereco());
    }

    @Test
    void quando_duas_notas_de_5_avaliacao_media_igual_5(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        mecanica.setNotas(new Integer[]{5,5});
        assertEquals(5.0, mecanica.getAvaliacaoMedia());
    }
    @Test
    void quando_primeira_nota_5_na_posicao_0_getNotas_5(){
        Mecanica mecanica = new Mecanica("Mecanica do Zé", "Rua do Zé, 123", "123456789", listaServicos, "123456789");
        mecanica.setNotas(new Integer[]{5,5});
        Integer notas[] = mecanica.getNotas();
        assertEquals(5, notas[0]);
    }

}