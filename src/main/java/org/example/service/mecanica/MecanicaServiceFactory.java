package org.example.service.mecanica;

public class MecanicaServiceFactory {

    private MecanicaServiceFactory(){
    }

    public static MecanicaService create(){
        return new MecanicaServiceImpl();
    }

}
