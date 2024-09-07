package org.example.dao.veiculodao;

import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {
    //TODO: create
    void create(Veiculo veiculo) throws SQLException;
    //TODO: read all
    List<Usuario> readAll() throws SQLException;
    //TODO: update
    void update(Veiculo veiculo) throws SQLException;
    //TODO: delete
    void delete(Long id) throws SQLException;
}
