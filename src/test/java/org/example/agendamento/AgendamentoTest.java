package org.example.agendamento;

import org.example.mecanica.Mecanica;
import org.example.servico.Servico;
import org.example.statusagendamento.StatusAgendamento;
import org.example.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {
    Usuario usuario = new Usuario("João", "Alberto", "joao@gmail.com", "12345678910", "123456789", "123456789", "123456789");
    Mecanica mecanica = new Mecanica("Mecânica do João", "Rua do João", "123456789", null, "123");
    LocalDateTime dataHora = LocalDateTime.now();
    Servico servico = new Servico("Troca de óleo", "Troca de óleo do motor", null, 100.0, 1);
    Servico servicosAgendados[] = {servico};
    StatusAgendamento status = StatusAgendamento.PENDENTE;
    @Test
    void quando_construtor_criado_campos_diferente_null(){
        Agendamento agendamento = new Agendamento(usuario, mecanica, dataHora, servicosAgendados, status, 1);
        Assertions.assertNotNull(agendamento.getUsuario());
        Assertions.assertNotNull(agendamento.getMecanica());
        Assertions.assertNotNull(agendamento.getDataHora());
        Assertions.assertNotNull(agendamento.getServicosAgendados());
        Assertions.assertNotNull(agendamento.getStatus());
        Assertions.assertNotNull(agendamento.getIdAgendamento());
    }

}