package org.example.dao.usuariodao;

import oracle.jdbc.internal.OracleTypes;
import org.example.config.DatabaseConnectionFactory;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioDaoImpl implements UsuarioDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria um usuário no banco de dados.
     *
     * @param usuario O usuário a ser criado.
     */
    @Override
    public Usuario create(Usuario usuario, Connection connection) throws SQLException, UsuarioNotSavedException {
        final String sql = "BEGIN INSERT INTO T_USUARIO (cpf, nm_usuario, email, dt_nasc, senha_login, telefone_usuario, endereco_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_usuario INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);

        try {
            call.setString(1, usuario.getCpf());
            call.setString(2, usuario.getNome());
            call.setString(3, usuario.getEmail());
            call.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento())); // Presumindo que dtNasc seja do tipo LocalDate
            call.setString(5, usuario.getSenha());
            call.setString(6, usuario.getTelefone());
            call.setString(7, usuario.getEndereco());
            call.registerOutParameter(8, OracleTypes.NUMBER); // O ID gerado será armazenado aqui

            int linhasAlteradas = call.executeUpdate();
            long id = call.getLong(8); // Recupera o ID gerado

            if (linhasAlteradas == 0 || id == 0) {
                throw new UsuarioNotSavedException();
            }

            usuario.setId(id);
            return usuario;
        } finally {
            call.close(); // Certifique-se de fechar o CallableStatement
        }
    }




    /**
     * Faz a leitura de todos os usuários no banco
     *
     * @return Uma lista de objetos da classe Usuario.
     */
    @Override
    public List<Usuario> readAll() {
        final List<Usuario> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_USUARIO";

        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            System.out.println("Conexão estabelecida com sucesso.");

            String databaseUser = conn.getMetaData().getUserName();
            System.out.println("Usuário do banco de dados: " + databaseUser);

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // Obtém os valores obrigatórios para o construtor
                Long id = resultSet.getLong("id_usuario"); // Corrigido
                String nome = resultSet.getString("nm_usuario");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha_login");
                String cpf = resultSet.getString("cpf");

                // Cria o usuário com os atributos obrigatórios
                Usuario usuario = new Usuario(id, nome, email, senha, cpf);

                // Verifica e define os valores opcionais se forem preenchidos
                String dataNascimento = resultSet.getString("dt_nasc");
                if (dataNascimento != null && !dataNascimento.trim().isEmpty()) {
                    usuario.setDataNascimento(dataNascimento);
                }

                String telefone = resultSet.getString("telefone_usuario"); // Corrigido
                if (telefone != null && !telefone.trim().isEmpty()) {
                    usuario.setTelefone(telefone);
                }

                String endereco = resultSet.getString("endereco_usuario");
                if (endereco != null && !endereco.trim().isEmpty()) {
                    usuario.alterarEndereco(endereco);
                }
                System.out.println("Usuário encontrado: " + nome);
                all.add(usuario);
            }
        } catch (SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de usuário: " + e.getMessage());
        }
        System.out.println("Total de usuários encontrados: " + all.size());
        return all;
    }


    /**
     * Atualiza um usuário no banco de dados.
     *
     * @param usuario O usuário a ser atualizado.
     */
    @Override
    public Usuario update(Usuario usuario, Connection connection) throws UsuarioNotFoundException, SQLException {
        final String sqlUpdate = "UPDATE t_usuario SET nm_usuario = ?, email = ?, dt_nasc = ?, senha_login = ?, telefone_usuario = ?, endereco_usuario = ? WHERE cpf = ?";
        final String sqlGetId = "SELECT id_usuario FROM t_usuario WHERE cpf = ?";

        PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
        pstmt.setString(1, usuario.getNome());
        pstmt.setString(2, usuario.getEmail());
        pstmt.setDate(3, convertToDate(usuario.getDataNascimento()));
        pstmt.setString(4, usuario.getSenha());
        pstmt.setString(5, usuario.getTelefone());
        pstmt.setString(6, usuario.getEndereco());
        pstmt.setString(7, usuario.getCpf());

        int linhasAlteradas = pstmt.executeUpdate();

        if (linhasAlteradas == 0) {
            throw new UsuarioNotFoundException();
        }

        // Agora obtemos o ID do usuário atualizado
        PreparedStatement pstmtGetId = connection.prepareStatement(sqlGetId);
        pstmtGetId.setString(1, usuario.getCpf());

        ResultSet rs = pstmtGetId.executeQuery();
        if (rs.next()) {
            usuario.setId(rs.getLong("id_usuario"));
        } else {
            throw new UsuarioNotFoundException();
        }

        // Fechar recursos
        rs.close();
        pstmtGetId.close();
        pstmt.close();

        return usuario;
    }



    /**
     * Deleta um usuário do banco de dados.
     *
     * @param cpf O CPF do usuário a ser deletado.
     */
    @Override
    public void delete(String cpf, Connection connection) throws UsuarioNotFoundException, SQLException {
        final String sql = "DELETE FROM T_USUARIO WHERE cpf = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cpf);

            int linhasAlteradas = pstmt.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new UsuarioNotFoundException();
            }
        }
    }

    @Override
    public Usuario findById(Long id, Connection connection) throws UsuarioNotFoundException, SQLException {
        final String sql = "SELECT * FROM T_USUARIO WHERE id_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nm_usuario");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha_login");
                    String cpf = resultSet.getString("cpf");

                    // Cria o objeto Usuario com os campos obrigatórios
                    usuario = new Usuario(id, nome, email, senha, cpf);

                    // Configura os campos opcionais, se presentes
                    String dataNascimento = resultSet.getString("dt_nasc");
                    if (dataNascimento != null && !dataNascimento.trim().isEmpty()) {
                        usuario.setDataNascimento(dataNascimento);
                    }
                    String telefone = resultSet.getString("telefone_usuario");
                    if (telefone != null && !telefone.trim().isEmpty()) {
                        usuario.setTelefone(telefone);
                    }
                    String endereco = resultSet.getString("endereco_usuario");
                    if (endereco != null && !endereco.trim().isEmpty()) {
                        usuario.alterarEndereco(endereco);
                    }
                } else {
                    throw new UsuarioNotFoundException();
                }
            }
        }
        System.out.println("O usuário encontrado no FindById é: " + usuario.toString());
        return usuario;
    }


    /**
     * Gera um hash SHA-256 para a senha fornecida.
     *
     * @param password A senha a ser hasheada.
     * @return O hash da senha truncado para 50 caracteres.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().substring(0, 50);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Falha em gerar o HASH.");
        }
    }

    /**
     * Converte a string de data para um objeto java.sql.Date.
     *
     * @param dateStr A string de data no formato "yyyy-MM-dd".
     * @return O objeto java.sql.Date correspondente.
     */
    private java.sql.Date convertToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null; // Retorna null caso a string esteja nula ou vazia
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateStr);
            return new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Erro em converter a data.");
        }
    }

}
