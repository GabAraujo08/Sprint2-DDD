package org.example.service.veiculo;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.veiculodao.VeiculoDao;
import org.example.dao.veiculodao.VeiculoDaoFactory;
import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.veiculo.VeiculoNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoDao dao = VeiculoDaoFactory.create();

    @Override
    public Veiculo create(Veiculo veiculo) throws VeiculoNotSavedException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try{
            veiculo = this.dao.create(veiculo, connection);
            connection.commit();
            return veiculo;
        }catch (SQLException | VeiculoNotSavedException e){
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Veiculo> readAll() {
        return this.dao.readAll();
    }

    @Override
    public Veiculo update(Veiculo veiculo) throws SQLException, VeiculoNotFoundException {
        return null;
    }

    @Override
    public void delete(String placa) throws SQLException, VeiculoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.delete(placa, connection);
        connection.commit();
    }
}
