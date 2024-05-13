package org.example.agendamento;

import org.example.categoriaservico.CategoriaServico;
import org.example.mecanica.Mecanica;
import org.example.servico.Servico;
import org.example.statusagendamento.StatusAgendamento;
import org.example.usuario.Usuario;


import java.lang.reflect.Array;
import java.time.LocalDateTime;

public class Agendamento {
    private Usuario usuario;
    private Mecanica mecanica;
    private LocalDateTime dataHora;
    private Servico servicosAgendados[];
    private StatusAgendamento status;
    private Integer idAgendamento;

    public Agendamento(Usuario usuario, Mecanica mecanica, LocalDateTime dataHora, Servico servicosAgendados[], StatusAgendamento status, Integer idAgendamento) {
        this.usuario = usuario;
        this.mecanica = mecanica;
        this.dataHora = dataHora;
        this.servicosAgendados = servicosAgendados;
        this.status = status;
        this.idAgendamento = idAgendamento;
    }



    //TESTANDO A INSTÂNCIA DA CLASSE AGENDAMENTO
    /*
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João", "Alberto", "joao@gmail.com", "12345678910", "123456789", "123456789", "123456789");
        Mecanica mecanica = new Mecanica("Mecânica do João", "Rua do João", "123456789", null, "123");
        LocalDateTime dataHora = LocalDateTime.now();
        Servico servico = new Servico("Troca de óleo", "Troca de óleo do motor", CategoriaServico.MECANICA, 100.0, 1);
        Servico servicosAgendados[] = {servico}; // Criando um Array que receberá objetos do tipo Servico

        StatusAgendamento status = StatusAgendamento.PENDENTE;

        Integer idAgendamento = 1;
        Agendamento agendamento = new Agendamento(usuario, mecanica, dataHora, servicosAgendados, status, idAgendamento);
        //TESTANDO SE O SERVICO FOI INSERIDO CORRETAMENTO DENTRO DE AGENDAMENTO
        System.out.println(agendamento.servicosAgendados[0].getNome());
    }
     */
}
