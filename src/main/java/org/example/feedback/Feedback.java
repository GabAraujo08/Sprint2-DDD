package org.example.feedback;

import org.example.mecanica.Mecanica;
import org.example.usuario.Usuario;

public class Feedback {
    private Usuario usuario;
    private Mecanica mecanica;
    private String comentarios;
    private Integer nota;
    private Integer idFeedback;

    public Feedback(Usuario usuario, Mecanica mecanica, String comentarios,Integer nota, Integer idFeedback) {
        this.usuario = usuario;
        this.mecanica = mecanica;
        this.comentarios = comentarios;
        if(!verificaNotasIsValid(nota)){
            throw new IllegalArgumentException("Nota inválida");
        }
        this.idFeedback = idFeedback;
    }

    private Boolean verificaNotasIsValid(Integer nota){
        if(nota < 0 || nota > 5){
            return false;
        }
        this.nota = nota;
        return true;

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Integer getNota() {
        return nota;
    }

    public Integer getIdFeedback() {
        return idFeedback;
    }
}
