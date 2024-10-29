package org.example.dao.veiculodao;

import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.veiculo.VeiculoNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {

    Veiculo create(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException;

    List<Veiculo> readAll();

    Veiculo update(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotFoundException;

    void delete(String placa, Connection connection) throws SQLException, VeiculoNotFoundException;
}
