package org.example.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void quando_construtor_todos_campos_diferente_null() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        Assertions.assertNotNull(usuario.getNome());
        Assertions.assertNotNull(usuario.getSobrenome());
        Assertions.assertNotNull(usuario.getEmail());
        Assertions.assertNotNull(usuario.getSenha());
        Assertions.assertNotNull(usuario.getDataNascimento());
        Assertions.assertNotNull(usuario.getCpf());
        Assertions.assertNotNull(usuario.getTelefone());
    }

    @Test
    void quando_excluir_conta_retorna_todos_campos_null() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
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
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        usuario.alterarNome("Gabriel");
        Assertions.assertEquals("Gabriel", usuario.getNome());
    }

    @Test
    void quando_alterar_sobrenome_silva_sobrenome_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        usuario.alterarSobrenome("Antunes");
        Assertions.assertEquals("Antunes", usuario.getSobrenome());
    }

    @Test
    void quando_alterar_email_gabriel_email_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        usuario.alterarEmail("josias@gmail.com");
        Assertions.assertEquals("josias@gmail.com", usuario.getEmail());
    }
    @Test
    void quando_alterar_telefone_123456789_telefone_alterado() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        usuario.alterarTelefone("987654321");
        Assertions.assertEquals("987654321", usuario.getTelefone());
    }
    @Test
    void quando_alterar_senha_123456_senha_alterada() {
        Usuario usuario = new Usuario("Carlos", "Silva", "calor@gmail.com", "123456", "01/01/2000", "12345678901", "123456789");
        usuario.alterarSenha("654321");
        Assertions.assertEquals("654321", usuario.getSenha());
    }
}