package org.example.feedback;

import org.example.mecanica.Mecanica;
import org.example.usuario.Usuario;

public class Feedback {
    private Usuario usuario;
    private Mecanica mecanica;
    private String comentarios;
    private Integer nota;
    private Integer idFeedback;

    public Feedback(Usuario usuario, Mecanica mecanica, String comentarios, Integer nota, Integer idFeedback) {
        this.usuario = usuario;
        this.mecanica = mecanica;
        this.comentarios = comentarios;
        this.nota = nota;
        this.idFeedback = idFeedback;
    }

    private Boolean verificaNotasIsValid(){
        if(nota < 0 || nota > 5){
            return false;
        }
        return true;
    }


}
