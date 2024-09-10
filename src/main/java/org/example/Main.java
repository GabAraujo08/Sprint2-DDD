package org.example;

import org.example.config.DatabaseConfig;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoImpl;
import org.example.entities.usuario.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            DatabaseConfig db = new DatabaseConfig(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    "rm558802",
                    "fiap24");

            connection = db.getConnection();
            System.out.println("Conexão com o banco de dados estabelecida.");

            Usuario usuario = new Usuario("Gabriel UPDATE", "gabriel5@gmail.com", "3402342309842384", "28062658007 ");
            System.out.println(usuario.getCpf());
            usuario.alterarEndereco("Rua dos bobos, nº 0");

            usuario.setTelefone("11999999999");
            usuario.setDataNascimento("1999-01-01");

            UsuarioDao usuarioDao = new UsuarioDaoImpl(connection);
//          usuarioDao.create(usuario);
            //System.out.println("Usuário criado com sucesso!");
            usuarioDao.update(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexão com o banco de dados fechada.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


//        try {
//            DatabaseConfig db = new DatabaseConfig(
//                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
//                    "rm558802",
//                    "fiap24");
//
//            connection = db.getConnection();
//            UsuarioDao usuarioDao = new UsuarioDaoImpl(connection);
//            List<Usuario> usuarios = usuarioDao.readAll();
//            for (Usuario usuario : usuarios) {
//                System.out.println(usuario);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}