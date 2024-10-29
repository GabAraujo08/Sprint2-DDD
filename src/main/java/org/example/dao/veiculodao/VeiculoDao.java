package org.example.dao.veiculodao;

import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.veiculo.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;

public interface VeiculoDao {

    Veiculo create(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException;

//    List<Usuario> readAll();
//
//    Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException;
//
//    Veiculo delete(Long id) throws SQLException, VeiculoNotFoundException;
}
