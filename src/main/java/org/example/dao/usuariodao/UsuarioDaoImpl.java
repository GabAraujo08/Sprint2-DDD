package org.example.dao.usuariodao;

import org.example.dao.usuariodao.UsuarioDao;
import org.example.entities.usuario.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {

    private final Connection connection;

    public UsuarioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        String sql = "insert into t_usuario(cpf, nm_usuario, email, dt_nasc, senha_login, nr_usuario, endereco_usuario) values(?,?,?,?,?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, usuario.getCpf());
        pstmt.setString(2, usuario.getNome());
        pstmt.setString(3, usuario.getEmail());
        pstmt.setString(4, usuario.getDataNascimento());
        pstmt.setString(5, usuario.getSenha());
        pstmt.setString(6, usuario.getTelefone());
        pstmt.setString(7, usuario.getEndereco());
        pstmt.executeUpdate();
    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        String sql = "SELECT * FROM t_usuario";
        List<Usuario> result = new ArrayList<>();
        Statement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
//            String id = rs.getLong("id");
//            String name = rs.getString("name");
//            int idade = rs.getInt("idade");
//            result.add(new Usuario(nome, sobrenome, email, senha));
        }
        return result;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
