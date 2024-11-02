package org.example.dao.mecanicadao;

import org.example.dao.usuariodao.UsuarioDaoImpl;

public class MecanicaDaoFactory {

    private MecanicaDaoFactory() {
    }

    public static MecanicaDao create(){
        return new MecanicaDaoImpl();
    }
}
