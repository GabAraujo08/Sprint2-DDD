package org.example.dao.servicodao;

import oracle.jdbc.internal.OracleTypes;
import org.example.config.DatabaseConnectionFactory;
import org.example.entities.servico.Servico;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.exceptions.servico.ServicoNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServicoDaoImpl implements ServicoDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Servico create(Servico servico, Connection connection) throws SQLException, ServicoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_SERVICO (nm_servico, descricao, categoria, valor_servico) "
                + "VALUES (?, ?, ?, ?) RETURNING id_servico INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);

        try {
            // Configurando os parâmetros para a inserção
            call.setString(1, servico.getNome());
            call.setString(2, servico.getDescricao());
            call.setString(3, servico.getCategoria());
            call.setDouble(4, servico.getValor());

            // Registrando o parâmetro de saída para capturar o ID gerado
            call.registerOutParameter(5, OracleTypes.NUMBER);

            int linhasAlteradas = call.executeUpdate();
            long id = call.getLong(5); // Recupera o ID gerado

            if (linhasAlteradas == 0 || id == 0) {
                throw new ServicoNotSavedException();
            }

            // Definindo o ID gerado no objeto Servico
            servico.setId(id);
            return servico;
        } finally {
            call.close(); // Fechando o CallableStatement
        }
    }

    @Override
    public List<Servico> readAll() {
        final List<Servico> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_SERVICO";

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            System.out.println("Conexão estabelecida com sucesso.");

            String databaseUser = conn.getMetaData().getUserName();
            System.out.println("Usuário do banco de dados: " + databaseUser);

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // Obtém os valores obrigatórios para o construtor
                Long id = resultSet.getLong("id_servico");
                String nome = resultSet.getString("nm_servico");
                String descricao = resultSet.getString("descricao");
                String categoria = resultSet.getString("categoria");
                Double valor = resultSet.getDouble("valor_servico");

                // Cria o serviço com os atributos obrigatórios
                Servico servico = new Servico(nome, descricao, categoria, valor, id);

                System.out.println("Serviço encontrado: " + nome);
                all.add(servico);
            }
        } catch (SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de serviço: " + e.getMessage());
        }
        System.out.println("Total de serviços encontrados: " + all.size());
        return all;
    }


    @Override
    public Servico update(Servico servico, Connection connection) throws SQLException, ServicoNotFoundException {
        // Declaração SQL para atualizar o serviço
        final String sqlUpdate = "UPDATE T_SERVICO SET nm_servico = ?, descricao = ?, categoria = ?, valor_servico = ? WHERE id_servico = ?";
        final String sqlGetId = "SELECT id_servico FROM T_SERVICO WHERE id_servico = ?";

        // Preparando a instrução para atualizar o serviço
        PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
        pstmt.setString(1, servico.getNome());
        pstmt.setString(2, servico.getDescricao());
        pstmt.setString(3, servico.getCategoria());
        pstmt.setDouble(4, servico.getValor());
        pstmt.setLong(5, servico.getId());

        // Executa o update e verifica se o serviço foi atualizado
        int linhasAlteradas = pstmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new ServicoNotFoundException();
        }

        // Verifica se o ID do serviço existe no banco após o update
        PreparedStatement pstmtGetId = connection.prepareStatement(sqlGetId);
        pstmtGetId.setLong(1, servico.getId());
        ResultSet rs = pstmtGetId.executeQuery();

        if (rs.next()) {
            servico.setId(rs.getLong("id_servico"));
        } else {
            throw new ServicoNotFoundException();
        }

        // Fechar recursos
        rs.close();
        pstmtGetId.close();
        pstmt.close();

        return servico;
    }


    @Override
    public void delete(Long id, Connection connection) throws ServicoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_SERVICO WHERE id_servico = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            int linhasAlteradas = pstmt.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new ServicoNotFoundException();
            }
        }
    }


    @Override
    public Servico findById(Long id, Connection connection) throws ServicoNotFoundException, SQLException {
        return null;
    }
}

