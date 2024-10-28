package org.example.entities.agendamento;

import org.example.entities.agendamento.Agendamento;
import org.example.entities.mecanica.Mecanica;
import org.example.entities.servico.Servico;
import org.example.entities.statusagendamento.StatusAgendamento;
import org.example.entities.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {
    Usuario usuario = new Usuario(1L ,"João", "joao@gmail.com", "232323232323", "31413468012");
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