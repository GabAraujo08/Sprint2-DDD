package org.example.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void quando_construtor_todos_campos_diferente_null() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        Assertions.assertNotNull(usuario.getNome());
        Assertions.assertNotNull(usuario.getSobrenome());
        Assertions.assertNotNull(usuario.getEmail());
        Assertions.assertNotNull(usuario.getSenha());

    }

    @Test
    void quando_excluir_conta_retorna_todos_campos_null() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        usuario.excluirConta();
        Assertions.assertNull(usuario.getNome());
        Assertions.assertNull(usuario.getSobrenome());
        Assertions.assertNull(usuario.getEmail());
        Assertions.assertNull(usuario.getSenha());
        Assertions.assertNull(usuario.getDataNascimento());
        Assertions.assertNull(usuario.getCpf());
        Assertions.assertNull(usuario.getTelefone());

    }

    @Test
    void quando_alterar_nome_gabriel_nome_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        usuario.alterarNome("Gabriel");
        Assertions.assertEquals("Gabriel", usuario.getNome());
    }

    @Test
    void quando_alterar_sobrenome_silva_sobrenome_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        usuario.alterarSobrenome("Antunes");
        Assertions.assertEquals("Antunes", usuario.getSobrenome());
    }

    @Test
    void quando_alterar_email_gabriel_email_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        usuario.alterarEmail("josias@gmail.com");
        Assertions.assertEquals("josias@gmail.com", usuario.getEmail());
    }

    @Test
    void quando_alterar_telefone_12345678789_telefone_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "12345678");
        usuario.alterarTelefone("987654321");
        Assertions.assertEquals("987654321", usuario.getTelefone());
    }

    @Test
    void quando_alterar_senha_1234567878_senha_alterada() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "1234567878");
        usuario.alterarSenha("654321");
        Assertions.assertEquals("654321", usuario.getSenha());
    }


    @Test
    void quando_cpf_invalido_no_setCpf_retorna_exception(){
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456787878");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            usuario.setCpf("75752345902");
        });
    }

    @Test
    void quando_cpf_valido_no_setCpf_nao_retorna_exception(){
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456787878");
        Assertions.assertDoesNotThrow(() -> {
            usuario.setCpf("65147436090"); //Esse CPF tem os requisitos necessários para passar no teste de validação.
        });
    }
    @Test
    void quando_usuario_criado_com_senha_menor_que_8_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "1");
        });
    }

    @Test
    void quando_usuario_criado_com_email_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Carlos", "Silva", "   ", "12345678");
        });
    }

    @Test
    void quando_usuario_criado_com_nome_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("    ", "Silva", "", "12345678");
        });
    }
    @Test
    void quando_usuario_criado_com_sobrenome_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Joao", "  ", "teste", "12345678");
        });
    }
    @Test
    void quando_usuario_criado_com_senha_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Joao", "Coutinho", "teste", "              ");
        });
    }
    @Test
    void quando_telefone_inserido_com_menos_de_11_digitos_retorna_exception() {
        Usuario usuario = new Usuario("Joao", "Teste", "teste", "12345678");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            usuario.setTelefone("(11)91111-000");
        });
    }
}