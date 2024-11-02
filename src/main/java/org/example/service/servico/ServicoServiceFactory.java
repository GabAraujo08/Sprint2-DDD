package org.example.service.servico;

public class ServicoServiceFactory {
    private ServicoServiceFactory() {
    }

    public static ServicoService create() {
        return new ServicoServiceImpl();
    }
}
