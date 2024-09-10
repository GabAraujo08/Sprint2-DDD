package org.example.dao.usuariodao;

import org.example.dao.usuariodao.UsuarioDao;
import org.example.entities.usuario.Usuario;
import org.example.entities.validadores.Validadores;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioDaoImpl implements UsuarioDao {

    private final Connection connection;

    public UsuarioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Faz a leitura de todos os usuários no banco
     *
     * @param usuario O usuário a ser criado.
     */
    @Override
    public void create(Usuario usuario) throws SQLException {

            System.out.println("Iniciando a criação do usuário.");
            String sql = "insert into t_usuario(cpf, nm_usuario, email, dt_nasc, senha_login, nr_usuario, endereco_usuario) values(?,?,?,?,?,?,?)";

            /*
                Eu criei todos estes souts pois meu código estava
                quebrando e eu não sabia exatamente onde, então foi uma forma
                de debugar que eu encontrei.
             */

            PreparedStatement pstmt = connection.prepareStatement(sql);
            System.out.println("Statement preparado com sucesso.");
            pstmt.setString(1, usuario.getCpf());
            System.out.println("CPF: " + usuario.getCpf() + " Passado com sucesso!");
            pstmt.setString(2, usuario.getNome());
            System.out.println("Nome: " + usuario.getNome() + " Passado com sucesso!");
            pstmt.setString(3, usuario.getEmail());
            System.out.println("Email: " + usuario.getEmail() + " Passado com sucesso!");
            pstmt.setDate(4, convertToDate(usuario.getDataNascimento()));
            System.out.println("Data de nascimento: " + usuario.getDataNascimento() + " Passado com sucesso!");
            pstmt.setString(5, hashPassword(usuario.getSenha()));
            System.out.println("Senha: " + usuario.getSenha() + " Passado com sucesso!");
            pstmt.setString(6, usuario.getTelefone());
            System.out.println("Telefone: " + usuario.getTelefone() + " Passado com sucesso!");
            pstmt.setString(7, usuario.getEndereco());
            System.out.println("Endereço: " + usuario.getEndereco() + " Passado com sucesso!");
            pstmt.executeUpdate();
            System.out.println("Statement executado com sucesso.");
//            connection.commit();
            System.out.println("Usuário criado no banco de dados.");

    }

    /**
     * Faz a leitura de todos os usuários no banco
     * @return Uma lista de objetos da classe Usuario.
     */
    @Override
    public List<Usuario> readAll() throws SQLException {
        String sql = "SELECT * FROM t_usuario";
        List<Usuario> result = new ArrayList<>();
        Statement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {

            String cpf = rs.getString("cpf").trim(); // Remove espaços no início e no final
            cpf = Validadores.removeCaracteresEspeciais(cpf); // Remove caracteres especiais
          //  System.out.println("CPF após remoção de caracteres especiais: " + cpf);  // Debug
            String nome = rs.getString("nm_usuario");
            String email = rs.getString("email");
            String senha = rs.getString("senha_login");

            Date dataNascimento = rs.getDate("dt_nasc");
            String numeroUsuario = rs.getString("nr_usuario");
            String endereco = rs.getString("endereco_usuario");

            Usuario usuario = new Usuario(nome, email, senha, cpf);

            /**
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
        return result;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql = "update t_usuario set nm_usuario = ?, email = ?, dt_nasc = ?, senha_login = ?, nr_usuario = ?, endereco_usuario = ? where cpf = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        System.out.println("Statement preparado com sucesso.");
        pstmt.setString(1, usuario.getNome());
        System.out.println("Nome: " + usuario.getNome() + " Passado com sucesso!");
        pstmt.setString(2, usuario.getEmail());
        System.out.println("Email: " + usuario.getEmail() + " Passado com sucesso!");
        pstmt.setDate(3, convertToDate(usuario.getDataNascimento()));
        System.out.println("Data de nascimento: " + usuario.getDataNascimento() + " Passado com sucesso!");
        pstmt.setString(4, hashPassword(usuario.getSenha()));
        System.out.println("Senha: " + usuario.getSenha() + " Passado com sucesso!");
        pstmt.setString(5, usuario.getTelefone());
        System.out.println("Telefone: " + usuario.getTelefone() + " Passado com sucesso!");
        pstmt.setString(6, usuario.getEndereco());
        System.out.println("Endereço: " + usuario.getEndereco() + " Passado com sucesso!");
        pstmt.setString(7, usuario.getCpf());
        System.out.println("CPF: " + usuario.getCpf() + " Passado com sucesso!");
        pstmt.executeUpdate();

        System.out.println("Statement executado com sucesso.");
    }

    @Override
    public void delete(Long id) throws SQLException {

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
            // Truncate the hash to 50 characters
            return hexString.toString().substring(0, 50);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
    /**
     * Converte a string de data para um objeto java.sql.Date.
     *
     * @param dateStr A string de data no formato "yyyy-MM-dd".
     * @return O objeto java.sql.Date correspondente.
     */
    private java.sql.Date convertToDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateStr);
            return new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date", e);
        }
    }
}
