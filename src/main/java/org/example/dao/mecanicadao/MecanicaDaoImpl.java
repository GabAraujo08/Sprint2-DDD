package org.example.dao.mecanicadao;

import oracle.jdbc.internal.OracleTypes;
import org.example.config.DatabaseConnectionFactory;
import org.example.entities.mecanica.Mecanica;
import org.example.exceptions.mecanica.MecanicaNotFoundException;
import org.example.exceptions.mecanica.MecanicaNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MecanicaDaoImpl implements MecanicaDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Mecanica create(Mecanica mecanica, Connection connection) throws SQLException, MecanicaNotSavedException {
        final String sql = "BEGIN INSERT INTO T_MECANICA (cnpj_mecanica, nm_mecanica, endereco_mecanica, telefone_mecanica) "
                + "VALUES (?, ?, ?, ?) RETURNING id_mecanica INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);

        try {
            // Configurando os parâmetros para a inserção
            call.setString(1, mecanica.getCnpjMecanica());
            call.setString(2, mecanica.getNome());
            call.setString(3, mecanica.getEndereco());
            call.setString(4, mecanica.getTelefone());

            // Registrando o parâmetro de saída para capturar o ID gerado
            call.registerOutParameter(5, OracleTypes.NUMBER);

            int linhasAlteradas = call.executeUpdate();
            long id = call.getLong(5); // Recupera o ID gerado

            if (linhasAlteradas == 0 || id == 0) {
                throw new MecanicaNotSavedException();
            }

            // Definindo o ID gerado no objeto Mecanica
            mecanica.setId(id);
            return mecanica;
        } finally {
            call.close(); // Fechando o CallableStatement
        }
    }


    @Override
    public List<Mecanica> readAll() {
        final List<Mecanica> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_MECANICA";

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            System.out.println("Conexão estabelecida com sucesso.");

            String databaseUser = conn.getMetaData().getUserName();
            System.out.println("Usuário do banco de dados: " + databaseUser);

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id_mecanica");
                String nome = resultSet.getString("nm_mecanica");
                String endereco = resultSet.getString("endereco_mecanica");
                String telefone = resultSet.getString("telefone_mecanica");
                String cnpjMecanica = resultSet.getString("cnpj_mecanica");

                Mecanica mecanica = new Mecanica(id, nome, endereco, telefone, cnpjMecanica);
                System.out.println("Mecânica encontrada: " + nome);
                all.add(mecanica);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao localizar registros: " + e.getMessage());
        }
        System.out.println("Total de mecânicas encontradas: " + all.size());
        return all;
    }



    @Override
    public Mecanica update(Mecanica mecanica, Connection connection) throws SQLException, MecanicaNotFoundException {

        final String sqlUpdate = "UPDATE T_MECANICA SET cnpj_mecanica = ?, nm_mecanica = ?, endereco_mecanica = ?, telefone_mecanica = ? WHERE id_mecanica = ?";
        final String sqlGetId = "SELECT id_mecanica FROM T_MECANICA WHERE id_mecanica = ?";


        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, mecanica.getCnpjMecanica());
            pstmt.setString(2, mecanica.getNome());
            pstmt.setString(3, mecanica.getEndereco());
            pstmt.setString(4, mecanica.getTelefone());
            pstmt.setLong(5, mecanica.getId());


            int linhasAlteradas = pstmt.executeUpdate();
            if (linhasAlteradas == 0) {
                throw new MecanicaNotFoundException();
            }
        }


        try (PreparedStatement pstmtGetId = connection.prepareStatement(sqlGetId)) {
            pstmtGetId.setLong(1, mecanica.getId());
            try (ResultSet rs = pstmtGetId.executeQuery()) {
                if (!rs.next()) {
                    throw new MecanicaNotFoundException();
                }
            }
        }

        return mecanica;
    }


    @Override
    public void delete(Long id, Connection connection) throws MecanicaNotFoundException, SQLException {

    }

    @Override
    public Mecanica findById(Long id, Connection connection) throws MecanicaNotFoundException, SQLException {
        return null;
    }
}
