package org.example.diagnostico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosticoTest {
    @Test
    void quando_construtor_criado_campos_diferente_null(){
        Diagnostico diagnostico = new Diagnostico("descricao", true, "informacaoColisao", 1);
        Assertions.assertNotNull(diagnostico.getDescricao());
        Assertions.assertNotNull(diagnostico.getColisao());
        Assertions.assertNotNull(diagnostico.getInformacaoColisao());
        Assertions.assertNotNull(diagnostico.getIdDiagnostico());
    }

}