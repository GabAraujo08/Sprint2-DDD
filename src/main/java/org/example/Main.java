package org.example;

import org.example.agendamento.Agendamento;
import org.example.categoriaservico.CategoriaServico;
import org.example.diagnostico.Diagnostico;
import org.example.feedback.Feedback;
import org.example.mecanica.Mecanica;
import org.example.orcamento.Orcamento;
import org.example.pecas.Peca;
import org.example.servico.Servico;
import org.example.statusagendamento.StatusAgendamento;
import org.example.usuario.Usuario;
import org.example.veiculo.Veiculo;

import java.time.LocalDateTime;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        Usuario usuario = new Usuario("João", "Alberto", "joao@gmail.com", "12345678910", "123456789", "123456789", "123456789");

        LocalDateTime dataHora = LocalDateTime.now();
        Servico servico = new Servico("Troca de óleo", "Troca de óleo do motor", CategoriaServico.MECANICA, 100.0, 1);
        Servico servicosAgendados[] = {servico};
        Mecanica mecanica = new Mecanica("Mecânica do João", "Rua do João", "123456789", servicosAgendados, "123");
        StatusAgendamento status = StatusAgendamento.PENDENTE;
        Agendamento agendamento = new Agendamento(usuario, mecanica, dataHora, servicosAgendados, status, 1);

        System.out.println("---------------------------------------------------");

        System.out.println("Nome do usuário no agendamento: " + agendamento.getUsuario().getNome());
        System.out.println("Nome da mecânica no agendamento: " + agendamento.getMecanica().getNome());
        System.out.println("Data e hora do agendamento: " + agendamento.getDataHora());
        System.out.println("Serviços agendados: "+ agendamento.getServicosAgendados()[0].getNome());
        System.out.println("Status do agendamento: " + agendamento.getStatus());
        System.out.println("ID do agendamento: " + agendamento.getIdAgendamento());

        System.out.println("---------------------------------------------------");
        Veiculo veiculo = new Veiculo("Chevrolet", "Onix", 2021, "ABC1234", "Preto", 0, usuario, "123456789");

        System.out.println("Marca do veículo: " + veiculo.getMarca());
        System.out.println("Modelo do veículo: " + veiculo.getModelo());
        System.out.println("Ano do veículo: " + veiculo.getAno());
        System.out.println("Placa do veículo: " + veiculo.getPlaca());
        System.out.println("Cor do veículo: " + veiculo.getCor());
        System.out.println("Kilometragem do veículo: " + veiculo.getKilometragem());
        System.out.println("Proprietário do veículo: " + veiculo.getProprietario().getNome());
        System.out.println("Chassi do veículo: " + veiculo.getChassi());

        System.out.println("---------------------------------------------------");

        System.out.println("Nome do usuário: " + usuario.getNome());
        System.out.println("Sobrenome do usuário: " + usuario.getSobrenome());
        System.out.println("Email do usuário: " + usuario.getEmail());
        System.out.println("CPF do usuário: " + usuario.getCpf());
        System.out.println("Telefone do usuário: " + usuario.getTelefone());
        System.out.println("Senha do usuário: " + usuario.getSenha());
        System.out.println("Data de nascimento do usuário: " + usuario.getDataNascimento());

        System.out.println("---------------------------------------------------");

        System.out.println("Nome da mecânica: " + mecanica.getNome());
        System.out.println("Endereço da mecânica: " + mecanica.getEndereco());
        System.out.println("Telefone da mecânica: " + mecanica.getTelefone());
        System.out.println("CNPJ da mecânica: " + mecanica.getCnpjMecanica());

        for (Servico servicos : mecanica.getServicos()) {
            System.out.println("Nome: " + servico.getNome() + ", Preço: " + servico.getValor() + ", Descrição: " + servico.getDescricao());
        }
        mecanica.setNotas(new Integer[]{5,5});
        System.out.println("Notas da mecânica: " + Arrays.toString(mecanica.getNotas()));
        System.out.println("Avaliação média da mecânica: " + mecanica.getAvaliacaoMedia());

        System.out.println("---------------------------------------------------");

        Feedback feedback = new Feedback(usuario, mecanica, "comentario", 5, 1);
        System.out.println("ID do feedback: " + feedback.getIdFeedback());
        System.out.println("Usuário do feedback: " + feedback.getUsuario().getNome());
        System.out.println("Mecânica do feedback: " + feedback.getMecanica().getNome());
        System.out.println("Comentário do feedback: " + feedback.getComentarios());
        System.out.println("Nota do feedback: " + feedback.getNota());

        System.out.println("---------------------------------------------------");

        Peca peca = new Peca("PEÇA", "descricao", 20.0, "xpto", 0);
        Peca pecaLista[] = {peca};
        Servico servico0 = new Servico("SERVICO 0", "descricao", CategoriaServico.MECANICA, 20.0, 0);
        Servico servico1 = new Servico("SERVICO 1", "descricao", CategoriaServico.ELETRICA, 20.0, 0);
        Servico servicoLista[] = {servico0, servico1};
        Orcamento orcamento = new Orcamento(mecanica, servicoLista, pecaLista, 1);
        System.out.println("ID do orçamento: " + orcamento.getIdOrcamento());
        System.out.println("Mecânica do orçamento: " + orcamento.getMecanica().getNome());

        for (Peca pecas : orcamento.getPecas()) {
            System.out.println("Nome: " + pecas.getNome() + ", Preço: " + pecas.getPreco() + ", Descrição: " + pecas.getDescricao());
        }
        for (Servico servicos : orcamento.getServicos()) {
            System.out.println("Nome: " + servicos.getNome() + ", Preço: " + servicos.getValor() + ", Descrição: " + servicos.getDescricao());
        }
        System.out.println("Valor do orçamento: " + orcamento.getValor());

        System.out.println("---------------------------------------------------");

        Diagnostico diagnostico = new Diagnostico("descricao", true, "informacaoColisao", 1);
        System.out.println("ID do diagnóstico: " + diagnostico.getIdDiagnostico());
        System.out.println("Descrição do diagnóstico: " + diagnostico.getDescricao());
        System.out.println("Colisão do diagnóstico: " + diagnostico.getColisao());
        System.out.println("Informação da colisão do diagnóstico: " + diagnostico.getInformacaoColisao());

        System.out.println("---------------------------------------------------");

        System.out.println("Nome da peça: " + peca.getNome());
        System.out.println("Descrição da peça: " + peca.getDescricao());
        System.out.println("Preço da peça: " + peca.getPreco());
        System.out.println("Código da peça: " + peca.getIdPeca());
        System.out.println("Marca da peça: " + peca.getMarca());

        System.out.println("---------------------------------------------------");

        System.out.println("Nome do serviço: " + servico.getNome());
        System.out.println("Descrição do serviço: " + servico.getDescricao());
        System.out.println("Categoria do serviço: " + servico.getCategoria());
        System.out.println("Valor do serviço: " + servico.getValor());
        System.out.println("ID do serviço: " + servico.getIdServico());







    }
}

