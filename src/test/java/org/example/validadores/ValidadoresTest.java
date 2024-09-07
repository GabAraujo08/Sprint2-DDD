package org.example.validadores;

import org.example.entities.validadores.Validadores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadoresTest {
    @Test
    void quando_cpf_invalido_isCPF_retorna_false() {
        Assertions.assertFalse(Validadores.isCPF("122934304"));
    }

}