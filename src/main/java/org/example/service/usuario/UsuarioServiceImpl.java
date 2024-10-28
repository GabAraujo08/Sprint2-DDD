package org.example.service.usuario;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDao dao = UsuarioDaoFactory.create();

    @Override
    public Usuario create(Usuario usuario) throws UsuarioNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            usuario = this.dao.create(usuario, connection);
            connection.commit();
            return usuario;
        }catch (SQLException | UsuarioNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Usuario> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException, UsuarioNotFoundException {
        return null;
    }

    @Override
    public void delete(String cpf) throws SQLException, UsuarioNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(cpf, connection);
        connection.commit();
    }
}
