package org.example.feedback;

import org.example.mecanica.Mecanica;
import org.example.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    Usuario carlos = new Usuario(null, null, null, null, null, null, null);
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