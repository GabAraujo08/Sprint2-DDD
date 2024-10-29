package org.example.service.veiculo;

import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.veiculo.VeiculoNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoService {

    Veiculo create(Veiculo veiculo) throws VeiculoNotSavedException, SQLException;

    List<Veiculo> readAll() ;

    Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException;

    void delete(String placa) throws SQLException, VeiculoNotFoundException;
}
