package org.example;

import org.example.config.DatabaseConfig;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoImpl;
import org.example.entities.usuario.Usuario;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig db = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm558802",
                "fiap24");

        Usuario usuario = new Usuario("Gabriel", "Araujo", "gabriel@gmail.com", "1234455664424");
        usuario.alterarEndereco("Rua dos bobos, nº 0");
        usuario.setCpf("57578246895");
        usuario.setTelefone("11999999999");

        UsuarioDao usuarioDao = new UsuarioDaoImpl(db.getConnection());
        usuarioDao.create(usuario);
    }
}
