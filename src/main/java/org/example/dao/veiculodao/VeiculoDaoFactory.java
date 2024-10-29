package org.example.dao.veiculodao;

public class VeiculoDaoFactory {
    private VeiculoDaoFactory() {
    }

    public static VeiculoDao create(){
        return new VeiculoDaoImpl();
    }
}
