package org.example.entities.usuario;

import org.example.entities.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void quando_construtor_todos_campos_diferente_null() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "34343434343423", "31413468012");
        Assertions.assertNotNull(usuario.getNome());
        Assertions.assertNotNull(usuario.getCpf());
        Assertions.assertNotNull(usuario.getEmail());
        Assertions.assertNotNull(usuario.getSenha());

    }

    @Test
    void quando_excluir_conta_retorna_todos_campos_null() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "34343434343423", "31413468012");
        usuario.excluirConta();
        Assertions.assertNull(usuario.getNome());
        Assertions.assertNull(usuario.getEndereco());
        Assertions.assertNull(usuario.getEmail());
        Assertions.assertNull(usuario.getSenha());
        Assertions.assertNull(usuario.getDataNascimento());
        Assertions.assertNull(usuario.getCpf());
        Assertions.assertNull(usuario.getTelefone());

    }

    @Test
    void quando_alterar_nome_gabriel_nome_alterado() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "34343434343423", "31413468012");
        usuario.alterarNome("Gabriel");
        Assertions.assertEquals("Gabriel", usuario.getNome());
    }

    @Test
    void quando_alterar_email_gabriel_email_alterado() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "34343434343423", "31413468012");
        usuario.alterarEmail("josias@gmail.com");
        Assertions.assertEquals("josias@gmail.com", usuario.getEmail());
    }


    @Test
    void quando_alterar_senha_1234567878_senha_alterada() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "12345678", "31413468012");
        usuario.alterarSenha("87654321");
        Assertions.assertEquals("87654321", usuario.getSenha());
    }

    @Test
    void quando_usuario_criado_com_senha_menor_que_8_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "1234567", "31413468012");
        });
    }

    @Test
    void quando_usuario_criado_com_email_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Carlos", "   ", "12345678", "31413468012");
        });
    }

    @Test
    void quando_usuario_criado_com_nome_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("   ", "calor@gmail.com", "12345678", "31413468012");
        });
    }
    @Test
    void quando_usuario_criado_com_senha_vazio_retorna_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "            ", "31413468012");
        });
    }
    @Test
    void quando_telefone_inserido_com_menos_de_11_digitos_retorna_exception() {
        Usuario usuario = new Usuario("Carlos", "calor@gmail.com", "12345678", "31413468012");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            usuario.setTelefone("(11)91111-000");
        });
    }
}