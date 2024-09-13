package org.example.dao.usuariodao;
import org.example.config.DatabaseConfig;
import org.example.entities.usuario.Usuario;
import org.example.entities.validadores.Validadores;
import org.example.exceptions.UsuarioDaoException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.example.entities.validadores.Validadores.isCPF;

public class UsuarioDaoImpl implements UsuarioDao {

    private DatabaseConfig db;

    public UsuarioDaoImpl(DatabaseConfig db) {
        this.db = db;
    }

    /**
     * Cria um usuário no banco de dados.
     * @param usuario O usuário a ser criado.
     */
    @Override
    public void create(Usuario usuario) {
            String sql = "insert into t_usuario(cpf, nm_usuario, email, dt_nasc, senha_login, nr_usuario, endereco_usuario) values(?,?,?,?,?,?,?)";
            try{
                Connection connection = db.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, usuario.getCpf());
                pstmt.setString(2, usuario.getNome());
                pstmt.setString(3, usuario.getEmail());
                pstmt.setDate(4, convertToDate(usuario.getDataNascimento()));
                pstmt.setString(5, hashPassword(usuario.getSenha()));
                pstmt.setString(6, usuario.getTelefone());
                pstmt.setString(7, usuario.getEndereco());
                pstmt.executeUpdate();
                connection.close();
            }catch (SQLException e){
                throw new UsuarioDaoException("Erro ao criar usuário.");
            }
    }
    /**
     * Faz a leitura de todos os usuários no banco
     * @return Uma lista de objetos da classe Usuario.
     */
    @Override
    public List<Usuario> readAll() {
        List<Usuario> result = new ArrayList<>();
        String sql = "SELECT * FROM t_usuario";
        try {
            Connection connection = db.getConnection();
            Statement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String cpf = rs.getString("cpf").trim(); // Remove espaços no início e no final
                cpf = Validadores.removeCaracteresEspeciais(cpf); // Remove caracteres especiais
                String nome = rs.getString("nm_usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha_login");
                Date dataNascimento = rs.getDate("dt_nasc");
                String numeroUsuario = rs.getString("nr_usuario");
                String endereco = rs.getString("endereco_usuario");
                Usuario usuario = new Usuario(nome, email, senha, cpf);
                /*
                 Essa parte do código é responsável por verificar se os campos
                 estão nulos, e caso não estejam, setar os valores no objeto,
                 isso evita que caso os campos estejam nulos, a aplicação tente
                 setar um valor nulo no objeto, o que geraria um erro.
                 */
                if (dataNascimento != null) {
                    usuario.setDataNascimento(dataNascimento.toString());
                }
                if (numeroUsuario != null) {
                    usuario.setTelefone(numeroUsuario);
                }
                if (endereco != null) {
                    usuario.alterarEndereco(endereco);
                }
                result.add(usuario);

            }
            connection.close();
        }catch(SQLException e){
            //e.printStackTrace();
            throw new UsuarioDaoException("Erro ao ler usuários.");
        }
        return result;
    }
    /**
     * Atualiza um usuário no banco de dados.
     * @param usuario O usuário a ser atualizado.
     */
    @Override
    public void update(Usuario usuario) {
        String sql = "update t_usuario set nm_usuario = ?, email = ?, dt_nasc = ?, senha_login = ?, nr_usuario = ?, endereco_usuario = ? where cpf = ?";
        try{
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setDate(3, convertToDate(usuario.getDataNascimento()));
            pstmt.setString(4, hashPassword(usuario.getSenha()));
            pstmt.setString(5, usuario.getTelefone());
            pstmt.setString(6, usuario.getEndereco());
            pstmt.setString(7, usuario.getCpf() + " "); //Esse espaço é adicionado pois o CPF no banco de dados tem um espaço no final pois ele é um char de 12 caracteres, e minha classe remove esse espaço na validação do CPF.
            int linhasAlteradas = pstmt.executeUpdate();
            // Verifica se o CPF foi encontrado e deletado
            if (linhasAlteradas == 0) {
                throw new UsuarioDaoException("Usuário não existe no banco.");
            }
            connection.close();
        }catch(SQLException e) {
            throw new UsuarioDaoException("Erro ao atualizar usuário.");
        }
    }
    /**
     * Deleta um usuário do banco de dados.
     * @param cpf O CPF do usuário a ser deletado.
     */
    @Override
    public void delete(String cpf) throws SQLException {
        String sql = "DELETE FROM t_usuario WHERE cpf = ?";
        try {
            cpf = Validadores.removeCaracteresEspeciais(cpf); // Remove os caracteres especiais
            cpf = cpf.trim(); // Remove espaços no início e no final
            if (!isCPF(cpf)) {
                throw new UsuarioDaoException("CPF inválido.");
            }
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // Adiciona espaço ao CPF porque no banco de dados ele tem espaço no final (É UM CHAR DE 12 CARACTERES)
            pstmt.setString(1, cpf + " ");
            // Executa a query e retorna o número de linhas afetadas
            int linhasAlteradas = pstmt.executeUpdate();
            // Verifica se o CPF foi encontrado e deletado
            if (linhasAlteradas == 0) {
                throw new UsuarioDaoException("CPF não encontrado no banco de dados.");
            }
            connection.close();
        } catch (SQLException e) {
            throw new UsuarioDaoException("Erro ao deletar usuário.");
        }
    }

    /**
     * Gera um hash SHA-256 para a senha fornecida.
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
     * @param dateStr A string de data no formato "yyyy-MM-dd".
     * @return O objeto java.sql.Date correspondente.
     */
    private java.sql.Date convertToDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateStr);
            return new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Erro em converter a data.");
        }
    }
}
