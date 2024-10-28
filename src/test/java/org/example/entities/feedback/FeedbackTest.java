package org.example.entities.feedback;

import org.example.entities.mecanica.Mecanica;
import org.example.entities.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FeedbackTest {
    Usuario carlos = new Usuario(1l, "Carlos", "carlos@gmail.com", "12345678", "31413468012");
    Mecanica mecanica = new Mecanica("nome", "endereco", "telefone", null, "cnpjMecanica");
    @Test
    void quando_construtor_criado_todos_campos_diferente_null(){
        Feedback feedback = new Feedback(carlos, mecanica, "comentario", 5, 1);
        Assertions.assertNotNull(feedback.getIdFeedback());
        Assertions.assertNotNull(feedback.getUsuario());
        Assertions.assertNotNull(feedback.getMecanica());
        Assertions.assertNotNull(feedback.getComentarios());
        Assertions.assertNotNull(feedback.getNota());

    }
    @Test
    void quando_num_menor_que_0_retorna_false(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Feedback feedback = new Feedback(carlos, mecanica, "comentario", -1, 1);
        });
    }
}