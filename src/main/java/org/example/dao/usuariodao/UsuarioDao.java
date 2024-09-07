

package org.example.dao.usuariodao;

import org.example.entities.usuario.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {
    //TODO: create
    void create(Usuario usuario) throws SQLException;
    //TODO: read all
    List<Usuario> readAll() throws SQLException;
    //TODO: update
    void update(Usuario usuario) throws SQLException;
    //TODO: delete
    void delete(Long id) throws SQLException;


}