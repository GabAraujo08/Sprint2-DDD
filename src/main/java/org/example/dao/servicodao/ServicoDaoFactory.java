package org.example.dao.servicodao;

import org.example.dao.usuariodao.UsuarioDaoImpl;

public class ServicoDaoFactory {

    private ServicoDaoFactory() {
    }

    public static ServicoDao create(){
        return new ServicoDaoImpl();
    }
}
