package org.example.entities.veiculo;

import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {
    Usuario carlos = new Usuario("Gabriel", "gabriel@gmail.com", "12345678", "31413468012");
    @Test
    void quando_construtor_todos_campos_diferente_null() {

        Veiculo veiculo = new Veiculo("Chevrolet", "Onix", 2021, "ABC1234", "Preto", 0, carlos, "123456789");
        Assertions.assertNotNull(veiculo.getKilometragem());
        Assertions.assertNotNull(veiculo.getMarca());
        Assertions.assertNotNull(veiculo.getModelo());
        Assertions.assertNotNull(veiculo.getAno());
        Assertions.assertNotNull(veiculo.getPlaca());
        Assertions.assertNotNull(veiculo.getCor());
        Assertions.assertNotNull(veiculo.getProprietario());
        Assertions.assertNotNull(veiculo.getChassi());
    }
    @Test
    void quando_km_100_retorna_100() {
        Veiculo veiculo = new Veiculo("Chevrolet", "Onix", 2021, "ABC1234", "Preto", 0, carlos, "123456789");
        veiculo.atualizarKilometragem(100);
        assertEquals(100, veiculo.getKilometragem());
    }
    @Test
    void excluir_veiculo_retorna_todos_campos_null() {
        Veiculo veiculo = new Veiculo("Chevrolet", "Onix", 2021, "ABC1234", "Preto", 0, carlos, "123456789");
        veiculo.excluirVeiculo();
        Assertions.assertNull(veiculo.getKilometragem());
        Assertions.assertNull(veiculo.getMarca());
        Assertions.assertNull(veiculo.getModelo());
        Assertions.assertNull(veiculo.getAno());
        Assertions.assertNull(veiculo.getPlaca());
        Assertions.assertNull(veiculo.getCor());
        Assertions.assertNull(veiculo.getProprietario());
        Assertions.assertNull(veiculo.getChassi());

    }
    @Test
    void alterar_cor_para_azul_retorna_azul() {
        Veiculo veiculo = new Veiculo("Chevrolet", "Onix", 2021, "ABC1234", "Preto", 0, carlos, "123456789");
        veiculo.alterarCor("Azul");
        assertEquals("Azul", veiculo.getCor());
    }

}